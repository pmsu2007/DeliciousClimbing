package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.repository.*;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final FileRepository fileRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final EquipmentFileRepository equipmentFileRepository;

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
    public Equipment getPostDetail(Long postId){
        TbEquipment entity = equipmentRepository.findById(postId).orElseThrow();

        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 생성
    public Equipment createPost(EquipmentDto dto){

        TbUser user = userRepository.findById(dto.getUserId()).orElse(null);
        TbAddress address = addressRepository.findByCode(dto.getAddressCode());
        TbEquipment entity = equipmentRepository.save(dto.toEntity(user,address));
        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 수정
    public Equipment updatePost(Long postId, EquipmentDto dto){
        TbEquipment entity = equipmentRepository.findById(postId).orElseThrow();
        TbAddress adress = addressRepository.findByCode(dto.getAddressCode());
        entity.update(dto.getTitle(),dto.getContent(),dto.getTradeCost(),dto.getTradeStatus(),adress);
        return Equipment.builder()
                .entity(entity)
                .build();
    }

    @Transactional  //게시글 삭제
    public void deletePost(Long postId){
        equipmentFileRepository.deleteByEquipment_Id(postId);
        equipmentRepository.deleteById(postId);
    }

    @Transactional  //파일 생성
    public EquipmentFile createFile(EquipmentFileDto dto){
        TbEquipment equipment = equipmentRepository.findById(dto.getPostId()).orElseThrow();

        TbEquipmentFile entity = equipmentFileRepository.save(dto.toEntity(equipment));

        return EquipmentFile.builder()
                .entity(entity)
                .build();
    }

    @Transactional //파일 수정
    public EquipmentFile updateFile(Long id, EquipmentFileDto dto){

        TbEquipmentFile entity = equipmentFileRepository.findById(id).orElseThrow();
        entity.update(dto.getFileName());
        return EquipmentFile.builder()
                .entity(entity)
                .build();

    }

    @Transactional //파일 삭제
    public void deleteFile(Long id){
        equipmentFileRepository.deleteByEquipment_Id(id);
    }

}

