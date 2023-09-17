package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;
import sangmyungdae.deliciousclimbing.dto.chat.ChatRoom;
import sangmyungdae.deliciousclimbing.dto.mate.*;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateService {

    private final MateRepository mateRepository;
    private final MateCommentRepository commentRepository;
    private final MateFileRepository fileRepository;
    private final MountainRepository mountainRepository;
    private final FamousMountainRepository fmtRepository;
    private final AddressRepository addressRepository;
    private final FamousMountainAddressRepository fmtAddressRepository;
    private final UserRepository userRepository;
    private final MateChatRepository mateChatRepository;
    private final MateChatRoomRepository mateChatRoomRepository;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbMate findMate(Long mateId) {
        return mateRepository.findById(mateId).orElseThrow(() -> ExceptionUtil.id(mateId, TbEquipment.class.getName()));
    }

    // 게시글 목록 조회 → Pagination 구현
    @Transactional
    public Page<MatePost> getMateList(MateSearchDto dto, Pageable pageable) {

        Page<TbMate> findMates;
        if (dto.getMountainId() == null && dto.isRecruitStatusFiltering() == false) {
            // 모든 글 반환
            findMates = mateRepository.findAll(pageable);
        } else if (dto.getMountainId() == null && dto.isRecruitStatusFiltering() == true) {
            // 산 필터링 X, 모집중인 게시글만 보기 O
            findMates = mateRepository.findPageByRecruitStatus(false, pageable);
        } else if (dto.getMountainId() != null && dto.isRecruitStatusFiltering() == false) {
            // 산 필터링 O, 모집중인 게시글만 보기 X
            findMates = mateRepository.findPageByFamousMountain_Id(dto.getMountainId(), pageable);
        } else {
            // 산 필터링 O, 모집중인 게시글만 보기 O
            findMates = mateRepository.findPageByFamousMountain_IdAndRecruitStatus(dto.getMountainId(), false, pageable);
        }

        return MatePost.toDtoList(findMates);
    }

    // 게시글 상세 조회
    @Transactional
    public MatePost getPostDetail(Long mateId) {

        TbMate tbMate = mateRepository.findById(mateId).orElseThrow(() -> new IllegalArgumentException("tbMate doesn't exist. mateId=" + mateId));

        // 데이터 조회 시, 조회수 증가
        tbMate.updateHit(tbMate.getHits() + 1);

        return MatePost.builder()
                .entity(tbMate)
                .build();
    }

    // 산 리스트 반환
    @Transactional
    public List<MateFamousMountain> getMountainList(String sido, String sigungu) {

        TbAddress tbAddress = addressRepository.findBySidoAndSigungu(sido, sigungu).orElseThrow(() -> new IllegalArgumentException("tbAddress doesn't exist. sido=" + sido + ", sigungu=" + sigungu));
        log.info("addressCode={}", tbAddress.getCode());
        // List<TbFamousMountainAddress>에서 명산 id를 추출하여 List로 반환
        List<Long> fmtIds = fmtAddressRepository.findByAddress(tbAddress)
                .stream().map(fmtAddress -> fmtAddress.getFamousMountain().getId())
                .collect(Collectors.toList());
        List<TbFamousMountain> findFamousMountainList = fmtRepository.findByIdIn(fmtIds);

        return MateFamousMountain.toDtoList(findFamousMountainList);
    }

    // 게시글 생성
    @Transactional
    public Mate createPost(Long userId, MateDto dto) {

//        TbMountain findMountain = mountainRepository.getReferenceById(dto.getMountainId());
        TbFamousMountain findMountain = fmtRepository.getReferenceById(dto.getMountainId());
        TbUser findUser = userRepository.getReferenceById(userId);

        TbMate entity = mateRepository.save(dto.toEntity(findUser, findMountain));

        return Mate.builder()
                .entity(entity)
                .build();
    }

    // 게시글 수정
    @Transactional
    public Mate updatePost(Long mateId, MateDto dto) {

        TbFamousMountain findFamousMountain = fmtRepository.getReferenceById(dto.getMountainId());
        TbMate tbMate = mateRepository.findById(mateId).orElseThrow(() -> new IllegalArgumentException("tbMate doesn't exist. mateId=" + mateId));
        tbMate.update(dto.getTitle(), dto.getContent(), dto.getRecruitCount(), dto.getRecruitStatus(), dto.getRecruitDate(), findFamousMountain);

        return Mate.builder()
                .entity(tbMate)
                .build();
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long mateId) {

        TbMate findMate = mateRepository.findById(mateId).orElseThrow(() -> new IllegalArgumentException("tbMate doesn't exist. mateId=" + mateId));
        List<Long> commentIds = findMate.getMateComments().stream().map(comment -> comment.getId()).collect(Collectors.toList());
        commentIds.stream().forEach(id -> deleteComment(findMate.getId(), id));
        List<Long> fileIds = findMate.getMateFiles().stream().map(file -> file.getId()).collect(Collectors.toList());
        fileIds.stream().forEach(id -> deleteFile(findMate.getId(), id));
        mateRepository.deleteById(mateId);
    }

    // 댓글 생성
    @Transactional
    public MateComment createComment(Long mateId, Long userId, MateCommentDto dto) {

        // Request DTO to Entity
        TbMate findMate = mateRepository.getReferenceById(mateId);
        TbUser findUser = userRepository.getReferenceById(userId);


        TbMateComment entity = commentRepository.save(dto.toEntity(findMate, findUser));

        // Entity to Response DTO
        return MateComment.builder()
                .entity(entity)
                .build();
    }

    // 댓글 수정
    @Transactional
    public MateComment updateComment(Long mateCommentId, MateCommentDto dto) {

        TbMateComment entity = commentRepository.findById(mateCommentId).orElseThrow(() -> new IllegalArgumentException("tbMateComment doesn't exist. mateCommentId=" + mateCommentId));
        entity.update(dto.getContent());

        return MateComment.builder()
                .entity(entity)
                .build();
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long mateId, Long commentId) {

        commentRepository.deleteById(commentId);
    }


    // 파일 생성
    @Transactional
    public MateFile createFile(Long mateId, MateFileDto dto) {

        TbMate findMate = mateRepository.getReferenceById(mateId);

        // Request Dto to Entity
        TbMateFile entity = fileRepository.save(dto.toEntity(findMate));

        // Entity to Response DTO
        return MateFile.builder()
                .entity(entity)
                .build();
    }

    // 파일 수정
    @Transactional
    public MateFile updateFile(Long fileId, MateFileDto dto) {

        TbMateFile entity = fileRepository.findById(fileId).orElseThrow(() -> new IllegalArgumentException("tbMateFile doesn't exist. fileId=" + fileId));
        entity.update(dto.getFileName());

        return MateFile.builder()
                .entity(entity)
                .build();
    }

    // 파일 삭제
    @Transactional
    public void deleteFile(Long mateId, Long fileId) {

        fileRepository.deleteById(fileId);
    }


    @Transactional
    public ChatRoom createChatting(Long mateId) {
        TbMate mate = findMate(mateId);
        TbUser customer = findUser(AuthUtil.getAuthUser());

        // 이미 개설된 방이 있을 때 로직,
        if (mateChatRoomRepository.existsByCreatorAndMate(customer, mate)) {
            TbMateChatRoom room = mateChatRoomRepository.findByCreatorAndMate(customer, mate)
                    .orElseThrow(() -> ExceptionUtil.available("You have no chatting room this post"));

            return ChatRoom.builder()
                    .type(ChatRoomType.MATE)
                    .mate(room)
                    .build();
        } else {
            TbMateChatRoom chatRoom = TbMateChatRoom.builder()
                    .creator(customer)
                    .mate(mate)
                    .roomName(mate.getTitle())
                    .currentCount(0)
                    .totalCount(mate.getRecruitCount())
                    .build();

            return ChatRoom.builder()
                    .type(ChatRoomType.EQUIPMENT)
                    .mate(mateChatRoomRepository.save(chatRoom))
                    .build();
        }
    }
}