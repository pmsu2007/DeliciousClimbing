package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.FileStore;

import javax.transaction.Transactional;
import java.util.Comparator;
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

    @Transactional
    public Page<Equipment> getPostList(EquipmentSearchDto dto, Pageable pageable) {
//        Page<TbEquipment> entities = equipmentRepository.findPageByAddress_CodeAndType(code,type ,pageable);
//        return Equipment.toDtoList(entities);
        if (dto.getEquipmentType() == null && dto.getAdressCode() == null) {
            Page<TbEquipment> findEquipments = equipmentRepository.findAll(pageable);
            return Equipment.toDtoList(findEquipments);
        } else if (dto.getEquipmentType() == null && dto.getAdressCode() != null) {
            Page<TbEquipment> findEquipments = equipmentRepository.findPageByAddress_Code(dto.getAdressCode(), pageable);
            return Equipment.toDtoList(findEquipments);
        } else if (dto.getEquipmentType() != null && dto.getAdressCode() == null) {
            Page<TbEquipment> findEquipments = equipmentRepository.findPageByType(dto.getEquipmentType(), pageable);
            return Equipment.toDtoList(findEquipments);
        } else {
            Page<TbEquipment> findEquipments = equipmentRepository.findPageByAddress_CodeAndType(dto.getAdressCode(), dto.getEquipmentType(), pageable);
            return Equipment.toDtoList(findEquipments);
        }
    }

    @Transactional  //게시글 상세 조회
    public Equipment getPostDetail(Long postId) {
        TbEquipment entity = equipmentRepository.findById(postId).orElseThrow();

        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 생성
    public Equipment createPost(EquipmentDto dto, MultipartFile[] multipartFiles) {
        TbUser user = userRepository.findById(dto.getUserId()).orElse(null);
        TbAddress address = addressRepository.findByCode(dto.getAddressCode()).orElse(null);
        TbEquipment entity = equipmentRepository.save(dto.toEntity(user, address));
        List<TbEquipmentFile> files = fileStore.storeFiles(multipartFiles).stream()
                .map(file -> TbEquipmentFile.builder()
                        .storeFileName(file.getStoreFileName())
                        .uploadFileName(file.getUploadFileName())
                        .build())
                .collect(Collectors.toList());

        TbEquipment equipment = TbEquipment.builder().type(dto.getType()).content(dto.getContent()).user(user).build();

        for (TbEquipmentFile file : files) {
            equipment.addFile(file);
        }

        return Equipment.builder().entity(equipmentRepository.save(equipment)).build();
    }

    @Transactional  //게시글 수정
    public Equipment updatePost(Long postId, EquipmentDto dto) {
        TbEquipment entity = equipmentRepository.findById(postId).orElseThrow();
        TbAddress adress = addressRepository.findByCode(dto.getAddressCode()).orElse(null);
        entity.update(dto.getTitle(), dto.getContent(), dto.getTradeCost(), dto.getTradeStatus(), adress);
        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 삭제
    public void deletePost(Long postId) {
        equipmentFileRepository.deleteByEquipment_Id(postId);
        equipmentRepository.deleteById(postId);
    }

}