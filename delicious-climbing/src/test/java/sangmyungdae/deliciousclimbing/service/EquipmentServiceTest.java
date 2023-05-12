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
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.Equipment;
import sangmyungdae.deliciousclimbing.dto.EquipmentDto;
import sangmyungdae.deliciousclimbing.dto.EquipmentFileDto;
import sangmyungdae.deliciousclimbing.repository.EquipmentFileRepository;
import sangmyungdae.deliciousclimbing.repository.EquipmentRepository;

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

    @BeforeEach
    void clean(){
        equipmentRepository.deleteAll();
        equipmentFileRepository.deleteAll();
    }
    @Test
    @DisplayName("글 1페이지 조회")
    void getPostList() {
        //given
//        Page<TbEquipment>

    }

    @Test
    @DisplayName("상세글조회")
    void getPostDetail() {
        //given
        TbEquipment tbequipment = TbEquipment.builder()
                .type(EquipmentType.CLOTHES)
                .title("제목1")
                .content("내용1")
                .tradeCost(1000)
                .tradeStatus(true)
                .address(TbAddress.builder().build())
                .user(TbUser.builder().build())
                .build();

        equipmentRepository.save(tbequipment);
        //when
        Equipment response = equipmentService.getPostDetail(tbequipment.getId());

        //then
        assertNotNull(response);
        assertEquals("제목1",response.getTitle());
        assertEquals("내용1",response.getContent());
        assertEquals(1000,response.getTradeCost());
        assertEquals(true,response.getTradeStatus());

    }

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
                .addressCode(1L)
                .userId(1L)
                .build();
        //when
        equipmentService.createPost(dto);

        //then
        assertEquals(1L,equipmentRepository.count());

        TbEquipment equipment = equipmentRepository.findAll().get(0);
        assertEquals("제목1",equipment.getTitle());
        assertEquals("내용1",equipment.getContent());
        assertEquals(1000,equipment.getTradeCost());
        assertEquals(true,equipment.isTradeStatus());

    }

    @Test
    @DisplayName("글수정")
    void updatePost() {
        //given
        TbEquipment tbequipment = TbEquipment.builder()
                .type(EquipmentType.CLOTHES)
                .title("제목1")
                .content("내용1")
                .tradeCost(1000)
                .tradeStatus(true)
                .address(TbAddress.builder().build())
                .user(TbUser.builder().build())
                .build();

        equipmentRepository.save(tbequipment);

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
        equipmentService.updatePost(tbequipment.getId(),edit);

        //then
        TbEquipment change = equipmentRepository.findById(tbequipment.getId())
                .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id = "+tbequipment.getId()));

        Assertions.assertEquals("제목2",change.getTitle());
        Assertions.assertEquals("내용2",change.getContent());


    }

    @Test
    @DisplayName("글삭제")
    void deletePost() {
        //given
        TbEquipment tbequipment = TbEquipment.builder()
                .type(EquipmentType.CLOTHES)
                .title("제목1")
                .content("내용1")
                .tradeCost(1000)
                .tradeStatus(true)
                .address(TbAddress.builder().build())
                .user(TbUser.builder().build())
                .build();

        equipmentRepository.save(tbequipment);

        //when
        equipmentService.deletePost(tbequipment.getId());

        //then
        Assertions.assertEquals(0,equipmentRepository.count());
    }

    @Test
    @DisplayName("파일생성")
    void createFile() {
        //given
        EquipmentFileDto dto = EquipmentFileDto.builder()
                .fileName("파일이름")
                .postId(1L)
                .build();
        //when
        equipmentService.createFile(dto);
        //then
        assertEquals(1L,equipmentFileRepository.count());

        TbEquipmentFile file = equipmentFileRepository.findAll().get(0);
        assertEquals("파일이름",file.getFileName());


    }

    @Test
    @DisplayName("파일수정")
    void updateFile() {
        TbEquipmentFile file = TbEquipmentFile.builder()
                .equipment(TbEquipment.builder().build())
                .fileName("파일이름")
                .build();
        equipmentFileRepository.save(file);
        EquipmentFileDto editfile = EquipmentFileDto.builder()
                .fileName("파일이름수정")
                .postId(1L)
                .build();
        //when
        equipmentService.updateFile(file.getId(),editfile);

        //then
        TbEquipmentFile change = equipmentFileRepository.findById(file.getId())
                .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id = "+file.getId()));

        Assertions.assertEquals("파일이름수정",change.getFileName());


    }

    @Test
    @DisplayName("파일삭제")
    void deleteFile() {
        //given
        TbEquipmentFile file = TbEquipmentFile.builder()
                                .equipment(TbEquipment.builder().build())
                                .fileName("파일이름")
                                .build();
        equipmentFileRepository.save(file);

        //when
        equipmentService.deleteFile(file.getId());
        //then
        Assertions.assertEquals(0,equipmentFileRepository.count());

    }
}