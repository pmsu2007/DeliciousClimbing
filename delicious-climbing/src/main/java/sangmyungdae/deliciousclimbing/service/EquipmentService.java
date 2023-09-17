package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;
import sangmyungdae.deliciousclimbing.dto.chat.ChatRoom;
import sangmyungdae.deliciousclimbing.dto.equipment.Equipment;
import sangmyungdae.deliciousclimbing.dto.equipment.EquipmentDto;
import sangmyungdae.deliciousclimbing.dto.equipment.EquipmentSearchDto;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;
import sangmyungdae.deliciousclimbing.util.FileStore;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final EquipmentFileRepository equipmentFileRepository;
    private final FileStore fileStore;

    private final EquipmentChatRepository equipmentChatRepository;
    private final EquipmentChatRoomRepository equipmentChatRoomRepository;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbEquipment findEquipment(Long equipmentId) {
        return equipmentRepository.findById(equipmentId).orElseThrow(() -> ExceptionUtil.id(equipmentId, TbEquipment.class.getName()));
    }

    @Transactional
    public List<Equipment> getPostList(EquipmentSearchDto dto) {
//        Page<TbEquipment> entities = equipmentRepository.findPageByAddress_CodeAndType(code,type ,pageable);
//        return Equipment.toDtoList(entities);
//         if(dto.getLatest().equals("latest")){
//            // 최신순
//            List<TbEquipment> findEquipments = equipmentRepository.findAllByOrderByCreatedDateDesc();
//
//            return findEquipments.stream()
//                    .map(Equipment::new)
//                    .collect(Collectors.toList());
//
//        } else if(dto.getLatest().equals("oldest")){
//             // 오래된 순
//            List<TbEquipment> findEquipments = equipmentRepository.findAllByOrderByCreatedDateAsc();
//
//            return findEquipments.stream()
//                    .map(Equipment::new)
//                    .collect(Collectors.toList());
//
//        }else
            if (dto.getEquipmentType() == null && dto.getAdressCode() == null) {
            List<TbEquipment> findEquipments = equipmentRepository.findAll();
            return findEquipments.stream()
                    .map(Equipment::new)
                    .collect(Collectors.toList());

        } else if (dto.getEquipmentType() == null && dto.getAdressCode() != null) {
            List<TbEquipment> findEquipments = equipmentRepository.findPageByAddress_Code(dto.getAdressCode());
            return findEquipments.stream()
                    .map(Equipment::new)
                    .collect(Collectors.toList());

        } else if (dto.getEquipmentType() != null && dto.getAdressCode() == null) {
            List<TbEquipment> findEquipments = equipmentRepository.findPageByType(dto.getEquipmentType());
            return findEquipments.stream()
                    .map(Equipment::new)
                    .collect(Collectors.toList());
        } else{
            List<TbEquipment> findEquipments = equipmentRepository.findPageByAddress_CodeAndType(dto.getAdressCode(), dto.getEquipmentType());
            return findEquipments.stream()
                    .map(Equipment::new)
                    .collect(Collectors.toList());
        }

    }

    @Transactional  //게시글 상세 조회
    public Equipment getPostDetail(Long equipmentId) {
        TbEquipment entity = findEquipment(equipmentId);
        entity.updateViews(entity.getViews()+1);

        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 생성
    public Equipment createPost(EquipmentDto dto, MultipartFile[] multipartFiles) {
        TbUser user = findUser(AuthUtil.getAuthUser());
        TbAddress address = addressRepository.findByCode(dto.getAddressCode()).orElse(null);
        List<TbEquipmentFile> files = fileStore.storeFiles(multipartFiles).stream()
                .map(file -> TbEquipmentFile.builder()
                        .storeFileName(file.getStoreFileName())
                        .uploadFileName(file.getUploadFileName())
                        .build())
                .collect(Collectors.toList());

        TbEquipment equipment = TbEquipment.builder().type(dto.getType()).title(dto.getTitle()).address(address).tradeCost(dto.getTradeCost()).tradeStatus(true).content(dto.getContent()).user(user).views(0L).build();

        for (TbEquipmentFile file : files) {
            equipment.addFile(file);
        }

        return Equipment.builder().entity(equipmentRepository.save(equipment)).build();
    }

    @Transactional  //게시글 수정
    public Equipment updatePost(Long equipmentId, EquipmentDto dto) {
        TbEquipment entity = findEquipment(equipmentId);
        TbAddress adress = addressRepository.findByCode(dto.getAddressCode()).orElse(null);
        entity.update(dto.getTitle(), dto.getContent(), dto.getTradeCost(), dto.getTradeStatus(), adress);
        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 삭제
    public void deletePost(Long equipmentId) {
        TbEquipment entity = findEquipment(equipmentId);

        if(!entity.getUser().getEmail().equals(AuthUtil.getAuthUser())){
            throw ExceptionUtil.available("Forbidden");
        }

        equipmentFileRepository.deleteAll(entity.getFiles());
        equipmentRepository.delete(entity);
    }

    @Transactional // 거래 상태 업데이트
    public void updateTradeStatus(Long equipmentId){
        TbEquipment entity = findEquipment(equipmentId);
        if(!entity.getUser().getEmail().equals(AuthUtil.getAuthUser())){
            throw ExceptionUtil.available("Forbidden");
        }
        entity.updateTradeStatus(!entity.isTradeStatus());
    }

    @Transactional
    public ChatRoom createChatting(Long equipmentId) {
        TbEquipment equipment = findEquipment(equipmentId);
        TbUser customer = findUser(AuthUtil.getAuthUser());

        // 이미 개설된 방이 있을 때 로직,
        if (equipmentChatRoomRepository.existsByCreatorAndEquipment(customer, equipment)) {
            TbEquipmentChatRoom room = equipmentChatRoomRepository.findByCreatorAndEquipment(customer, equipment)
                    .orElseThrow(() -> ExceptionUtil.available("You have no chatting room this post"));

            return ChatRoom.builder()
                    .type(ChatRoomType.EQUIPMENT)
                    .equipment(room)
                    .build();
        } else {
            TbEquipmentChatRoom chatRoom = TbEquipmentChatRoom.builder()
                    .creator(customer)
                    .equipment(equipment)
                    .roomName(equipment.getTitle())
                    .currentCount(0)
                    .totalCount(2)
                    .build();

            return ChatRoom.builder()
                    .type(ChatRoomType.EQUIPMENT)
                    .equipment(equipmentChatRoomRepository.save(chatRoom))
                    .build();
        }
    }

//    // 최신순으로 정렬된 게시글 목록 가져오기
//    public List<Equipment> getLatestPosts() {
//        List<TbEquipment> findEquipments = equipmentRepository.findAllByOrderByCreatedDateDesc();
//
//        return findEquipments.stream()
//                .map(Equipment::new)
//                .collect(Collectors.toList());
//    }
//
//    // 오래된 순으로 정렬된 게시글 목록 가져오기
//    public List<Equipment> getOldestPosts() {
//        List<TbEquipment> findEquipments = equipmentRepository.findAllByOrderByCreatedDateAsc();
//
//        return findEquipments.stream()
//                .map(Equipment::new)
//                .collect(Collectors.toList());
//    }

}