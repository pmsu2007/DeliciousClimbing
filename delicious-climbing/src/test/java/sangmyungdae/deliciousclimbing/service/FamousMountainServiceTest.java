package sangmyungdae.deliciousclimbing.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Region;
import sangmyungdae.deliciousclimbing.dto.famousMountain.FamousMountain;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.repository.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
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
    UserRepository userRepository;
    @Autowired
    FamousMountainService service;

    @Test
    void getMountainList() {
    }

    @Test
    void getMountainDetail() {
        //given
        TbUser tbUser = TbUser.builder()
                .type(LoginType.SINGIN)
                .email("user1@test.com")
                .password("1234")
                .nickname("user1")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now())
                .build();
        userRepository.save(tbUser);

        TbFamousMountain tbFamousMountain = TbFamousMountain.builder()
                .name("북한산")
                .height((int) 835.6)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .region(Region.SEOUL_GYEONGGI)
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

        service.insertLike(tbFamousMountain.getId(), tbUser.getId());

        //when
        FamousMountain famousMountain1 = service.getMountainDetail(tbFamousMountain.getId(), tbUser.getId());
        FamousMountain famousMountain2 = service.getMountainDetail(tbFamousMountain.getId(), null);

        //then
        log.info("famousMountain1={}", famousMountain1);
        log.info("famousMountain2={}", famousMountain2);
        //연관매핑 되어있는것들이 안들어ㅗㅁ


    }

    @Test
    void insertLike() {
        //given
        TbUser tbUser = TbUser.builder()
                .type(LoginType.SINGIN)
                .email("user1@test.com")
                .password("1234")
                .nickname("user1")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now())
                .build();
        userRepository.save(tbUser);

        TbFamousMountain tbFamousMountain = TbFamousMountain.builder()
                .name("북한산")
                .height((int) 835.6)
                .info("testInfo")
                .difficulty(Difficulty.MIDDLE)
                .period(5)
                .reason("testReason")
                .region(Region.SEOUL_GYEONGGI)
                .build();
        famousMountainRepository.save(tbFamousMountain);

        //when
        service.insertLike(tbFamousMountain.getId(), tbUser.getId());
        Optional<TbFamousMountainLike> findLike = famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(tbUser.getId(), tbFamousMountain.getId());

        //then
        assertThat(findLike.isPresent()).isTrue();
        assertThatThrownBy(() -> service.insertLike(tbFamousMountain.getId(), tbUser.getId())).isInstanceOf(IllegalStateException.class);


    }

    @Test
    void deleteLike() {
    }

    @Test
    void getRecommendFamousMountains() {
    }
}