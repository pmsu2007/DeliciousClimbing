package sangmyungdae.deliciousclimbing.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.dto.*;
import sangmyungdae.deliciousclimbing.repository.*;

@Service
@RequiredArgsConstructor
public class MateService {
    private final MateRepository mateRepository;
    private final MateCommentRepository commentRepository;
    private final MateFileRepository fileRepository;
    private final MountainRepository mountainRepository;
    private final MountainAddressRepository mountainAddressRepository;
    private final AddressRepository addressRepository;

    // 게시글 목록 조회 → Pagination 구현
    @Transactional
    public Page<MatePost> getMateEntities(MateSearchDto dto, Pageable pageable) {


        return null;
    }

    // 게시글 상세 조회
    @Transactional
    public MatePost getOrPostThrow(Long postId) {

        return null;
    }

    // 게시글 생성
    @Transactional
    public Mate createOrPostThrow(MateDto dto) {

        return null;
    }

    // 게시글 수정
    @Transactional
    public Mate updateOrPostThrow(MateDto dto) {

        return null;
    }

    // 게시글 삭제
    @Transactional
    public Mate deleteOrPostThrow(Long postId) {
        // 댓글 삭제 후 게시글 삭제

        return null;
    }

    // 댓글 생성
    @Transactional
    public MateComment createCommentOrThrow(Long postId, CommentDto dto) {

        return null;
    }

    // 댓글 수정
    @Transactional
    public MateComment updateCommentOrThrow(Long postId, CommentDto dto) {

        return null;
    }

    // 댓글 삭제
    @Transactional
    public MateComment deleteCommentOrThrow(Long postId, Long commentId) {

        return null;
    }


    // 파일 생성
    @Transactional
    public MateFile createFileOrThrow(Long postId, FileDto dto) {

        return null;
    }

    // 파일 수정
    @Transactional
    public MateFile updateFileOrThrow(Long postId, FileDto dto) {

        return null;
    }

    // 파일 삭제
    @Transactional
    public MateFile deleteFileOrThrow(Long postId, Long fileId) {

        return null;
    }
}