package sangmyungdae.deliciousclimbing.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.*;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.dto.user.UserRegister;
import sangmyungdae.deliciousclimbing.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/*
@Slf4j
@SpringBootTest
class FamousMountainServiceTest {

    @Autowired
    FamousMountainRepository famousMountainRepository;
    @Autowired
    FamousMountainAddressRepository famousMountainAddressRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FamousMountainLikeRepository famousMountainLikeRepository;
    @Autowired
    FamousMountainRegionRepository famousMountainRegionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FamousMountainService service;
    @Autowired
    UserService userService;

    @AfterEach
    void deleteRepository() {
        famousMountainLikeRepository.deleteAll();
        famousMountainAddressRepository.deleteAll();
        famousMountainRegionRepository.deleteAll();
        addressRepository.deleteAll();
        famousMountainRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getMountainList() {
        //given
        User user = userService.createUser(UserRegister.builder()
                .email("pmsu2008")
                .password("1234")
                .type(LoginType.SINGIN)
                .nickname("민스님")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now()).build());

        TbFamousMountain mt1 = TbFamousMountain.builder()
                .name("가산")
                .height((int) 100)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(mt1);

        TbFamousMountain mt2 = TbFamousMountain.builder()
                .name("나산")
                .height((int) 200)
                .info("testInfo")
                .difficulty(Difficulty.UPPER)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(mt2);

        TbFamousMountain mt3 = TbFamousMountain.builder()
                .name("다산")
                .height((int) 300)
                .info("testInfo")
                .difficulty(Difficulty.LOWER)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(mt3);

        famousMountainRegionRepository.save(TbFamousMountainRegion.builder().famousMountain(mt1).region(Region.GANGWON).build());
        famousMountainRegionRepository.save(TbFamousMountainRegion.builder().famousMountain(mt1).region(Region.SEOUL_GYEONGGI).build());
        famousMountainRegionRepository.save(TbFamousMountainRegion.builder().famousMountain(mt2).region(Region.SEOUL_GYEONGGI).build());
        famousMountainRegionRepository.save(TbFamousMountainRegion.builder().famousMountain(mt3).region(Region.JEJU).build());

        //when
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.ASC, "name");
        // keyword x, region x
        List<FamousMountainList> mtList1 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);
        // keyword x, region o
        List<FamousMountainList> mtList2 = service.getMountainList(FamousMountainSearchDto.builder().region(Region.SEOUL_GYEONGGI).build(), pageable);
        // keyword o, region x
        List<FamousMountainList> mtList3 = service.getMountainList(FamousMountainSearchDto.builder().keyword("가").build(), pageable);
        // keyword o, region o
        List<FamousMountainList> mtList4 = service.getMountainList(FamousMountainSearchDto.builder().keyword("가").region(Region.JEJU).build(), pageable);

        // 가나다순
        pageable = PageRequest.of(0, 100, Sort.Direction.ASC, "name");
        List<FamousMountainList> mtList5 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);
        // 높이순
        pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "height");
        List<FamousMountainList> mtList6 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);
        // 난이도순 (상 -> 하)
        pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "difficulty");
        List<FamousMountainList> mtList7 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);
        // 난이도순 (하 -> 상)
        pageable = PageRequest.of(0, 100, Sort.Direction.ASC, "difficulty");
        List<FamousMountainList> mtList8 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);
        // 추천수 순
        pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "likes");
        List<FamousMountainList> mtList9 = service.getMountainList(FamousMountainSearchDto.builder().build(), pageable);

        //then
        assertThat(3).isEqualTo(mtList1.size());
        assertThat(2).isEqualTo(mtList2.size());
        assertThat(1).isEqualTo(mtList3.size());
        assertThat(0).isEqualTo(mtList4.size());
        log.info("mtList1={}", mtList1);
        log.info("mtList2={}", mtList2);
        log.info("mtList3={}", mtList3);
        log.info("mtList4={}", mtList4);
        log.info("mtList5={}", mtList5);
        log.info("mtList6={}", mtList6);
        log.info("mtList7={}", mtList7);
        log.info("mtList8={}", mtList8);
        log.info("mtList9={}", mtList9);


    }

    @Test
    void getMountainDetail() {
        //given
        User user = userService.createUser(UserRegister.builder().entity()
                .role(Role.USER)
                .email("pmsu2008")
                .password("1234")
                .type(LoginType.SINGIN)
                .nickname("민스님")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now()).build());

        TbFamousMountain tbFamousMountain = TbFamousMountain.builder()
                .name("북한산")
                .height((int) 835.6)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(tbFamousMountain);

        TbAddress gb = addressRepository.save(TbAddress.builder()
                .code(1130500000L)
                .sido("서울특별시")
                .sigungu("강북구")
                .build());
        TbAddress sb = addressRepository.save(TbAddress.builder()
                .code(1129000000L)
                .sido("서울특별시")
                .sigungu("성북구")
                .build());
        TbAddress jr = addressRepository.save(TbAddress.builder()
                .code(1111000000L)
                .sido("서울특별시")
                .sigungu("종로구")
                .build());
        TbAddress gy = addressRepository.save(TbAddress.builder()
                .code(4128000000L)
                .sido("경기도")
                .sigungu("고양시")
                .build());
        TbAddress yj = addressRepository.save(TbAddress.builder()
                .code(4163000000L)
                .sido("경기도")
                .sigungu("양주시")
                .build());

        famousMountainAddressRepository.save(TbFamousMountainAddress.builder()
                .famousMountain(tbFamousMountain)
                .address(gb)
                .build());
        famousMountainAddressRepository.save(TbFamousMountainAddress.builder()
                .famousMountain(tbFamousMountain)
                .address(sb)
                .build());
        famousMountainAddressRepository.save(TbFamousMountainAddress.builder()
                .famousMountain(tbFamousMountain)
                .address(jr)
                .build());
        famousMountainAddressRepository.save(TbFamousMountainAddress.builder()
                .famousMountain(tbFamousMountain)
                .address(gy)
                .build());
        famousMountainAddressRepository.save(TbFamousMountainAddress.builder()
                .famousMountain(tbFamousMountain)
                .address(yj)
                .build());

        service.insertLike(tbFamousMountain.getId(), user.getId());

        //when
        FamousMountain famousMountain1 = service.getMountainDetail(tbFamousMountain.getId(), user.getId());
        FamousMountain famousMountain2 = service.getMountainDetail(tbFamousMountain.getId(), null);

        //then
        log.info("famousMountain1={}", famousMountain1);
        log.info("famousMountain2={}", famousMountain2);


    }

    @Test
    void insertLike() {
        //given
        User user = userService.createUser(UserRegister.builder()
                .role(Role.USER)
                .email("pmsu2008")
                .password("1234")
                .type(LoginType.SINGIN)
                .nickname("민스님")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now()).build());

        TbFamousMountain tbFamousMountain = TbFamousMountain.builder()
                .name("북한산")
                .height((int) 835.6)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(tbFamousMountain);

        //when
        service.insertLike(tbFamousMountain.getId(), user.getId());
        Optional<TbFamousMountainLike> findLike = famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(user.getId(), tbFamousMountain.getId());

        //then
        assertThat(findLike.isPresent()).isTrue();
        assertThatThrownBy(() -> service.insertLike(tbFamousMountain.getId(), user.getId())).isInstanceOf(IllegalStateException.class);


    }

    @Test
    void deleteLike() {
        //given
        User user = userService.createUser(UserRegister.builder()
                .role(Role.USER)
                .email("pmsu2008")
                .password("1234")
                .type(LoginType.SINGIN)
                .nickname("민스님")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now()).build());

        TbFamousMountain tbFamousMountain = TbFamousMountain.builder()
                .name("북한산")
                .height((int) 835.6)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .build();
        famousMountainRepository.save(tbFamousMountain);

        service.insertLike(tbFamousMountain.getId(), user.getId());

        //when
        service.deleteLike(tbFamousMountain.getId(), user.getId());
        Optional<TbFamousMountainLike> findLike = famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(user.getId(), tbFamousMountain.getId());

        //then
        assertThat(findLike).isEmpty();
        assertThatThrownBy(() -> service.deleteLike(tbFamousMountain.getId(), user.getId())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getRecommendFamousMountains() {
    }
}
*/
