package sangmyungdae.deliciousclimbing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.Equipment;
import sangmyungdae.deliciousclimbing.dto.EquipmentDto;
import sangmyungdae.deliciousclimbing.dto.EquipmentFileDto;

import sangmyungdae.deliciousclimbing.dto.EquipmentSearchDto;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.EquipmentFileRepository;
import sangmyungdae.deliciousclimbing.repository.EquipmentRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;


import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipmentServiceTest {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentFileRepository equipmentFileRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("글생성")
    void createPost() {
        //given
        EquipmentDto dto = EquipmentDto.builder()
                .type(EquipmentType.CLOTHES)
                .title("제목1")
                .content("내용1")
                .tradeCost(1000)
                .tradeStatus(true)
                .addressCode(20392028L)
                .userId(1L)
                .build();
        //when
//        Equipment testDto = equipmentService.createPost(dto,files);
        //soutv + enter
//        System.out.println("testDto = " + testDto);
        //then
//        assertEquals(1L,equipmentRepository.count());
//
//        TbEquipment equipment = equipmentRepository.findAll().get(0);
//        assertEquals("제목1",equipment.getTitle());
//        assertEquals("내용1",equipment.getContent());
//        assertEquals(1000,equipment.getTradeCost());
//        assertEquals(true,equipment.isTradeStatus());

    }

    @Test
    @DisplayName(value = "글 목록 조회")
    void getPostList() {
        //given
        EquipmentSearchDto all = EquipmentSearchDto.builder().build();
        EquipmentSearchDto type = EquipmentSearchDto.builder().equipmentType(EquipmentType.CLOTHES).build();
        EquipmentSearchDto address = EquipmentSearchDto.builder().adressCode(20392028L).build();
        EquipmentSearchDto typeAndAddress = EquipmentSearchDto.builder().equipmentType(EquipmentType.CLOTHES).adressCode(20392028L).build();
        Pageable pageable = PageRequest.of(0, 5);
        //when
        Page<Equipment> posts = equipmentService.getPostList(typeAndAddress, pageable);

        //then
        for (Equipment equipment : posts) {
            System.out.println("equipment = " + equipment);
        }

    }

    @Test
    @DisplayName("상세글조회")
    void getPostDetail() {
        //given

        //when
        Equipment response = equipmentService.getPostDetail(3L);

        //then
        System.out.println("response = " + response);
//        assertNotNull(response);
//        assertEquals("제목1",response.getTitle());
//        assertEquals("내용1",response.getContent());
//        assertEquals(1000,response.getTradeCost());
//        assertEquals(true,response.getTradeStatus());

    }


    @Test
    @DisplayName("글수정")
    void updatePost() {
        //given


        EquipmentDto edit = EquipmentDto.builder()
                .type(EquipmentType.CLOTHES)
                .title("제목2")
                .content("내용2")
                .tradeCost(1000)
                .tradeStatus(true)
                .addressCode(1L)
                .userId(1L)
                .build();


        //when
        equipmentService.updatePost(3L, edit);

        //then
        TbEquipment change = equipmentRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + 3L));

        Assertions.assertEquals("제목2", change.getTitle());
        Assertions.assertEquals("내용2", change.getContent());


    }

    @Test
    @DisplayName("글삭제")
    void deletePost() {
        //given


        //when
        equipmentService.deletePost(4L);

        //then
        Assertions.assertEquals(1, equipmentRepository.count());
    }

    @Test
    @DisplayName("파일생성")
    void createFile() {
        //given
        EquipmentFileDto dto = EquipmentFileDto.builder()
                .fileName("파일이름")
                .postId(3L)
                .build();
        //when
        equipmentService.createFile(dto);
        //then
        System.out.println("dto = " + dto);
//        assertEquals(1L,equipmentFileRepository.count());
//
//        TbEquipmentFile file = equipmentFileRepository.findAll().get(0);
//        assertEquals("파일이름",file.getFileName());


    }

    @Test
    @DisplayName("파일수정")
    void updateFile() {

        EquipmentFileDto editfile = EquipmentFileDto.builder()
                .fileName("파일이름수정")
                .postId(3L)
                .build();
        //when
        equipmentService.updateFile(2L, editfile);

        //then
        TbEquipmentFile change = equipmentFileRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + 3L));

        Assertions.assertEquals("파일이름수정", change.getFileName());


    }

    @Test
    @DisplayName("파일삭제")
    void deleteFile() {
        //given

        //when
        equipmentService.deleteFile(3L); //postId 기준으로 삭제
        //then
        Assertions.assertEquals(0, equipmentFileRepository.count());

    }
}
