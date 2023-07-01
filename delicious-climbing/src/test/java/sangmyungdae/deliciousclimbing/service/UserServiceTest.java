package sangmyungdae.deliciousclimbing.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.*;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FamousMountainRepository famousMountainRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName(value="회원가입")
    void join() {
        // given
//        UserRegister register = UserRegister.builder()
//                .role(Role.USER)
//                .email("pmsu2008")
//                .password("1234")
//                .type(LoginType.SINGIN)
//                .nickname("민스님")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now()).build();
//        // when
//        User user = userService.createUser(register);
//
//        // then
//        System.out.println("user = " + user);
    }

    @Test
    @DisplayName(value = "회원 정보 수정")
    void updateUser() {
        //given
        UserDto userDto = UserDto.builder()
                .nickname("mingxoo")
                .imageUrl("https://google.image.url")
                .introduction("안녕하세요")
                .difficulty(Difficulty.UPPER)
                .sns("mingxoop")
                .famousMountainId(1L)
                .addressCode(1L)
                .build();

        // when
        User updateUser = userService.updateUser(1L, userDto);
        // then
        System.out.println("updateUser = " + updateUser);
    }

    @Test
    @DisplayName(value = "회원 조회")
    void findUser() {
        userService.getUser(2L);
    }

    @Test
    @DisplayName(value = "회원 로그인")
    void login() {
        //given
//        UserSign sign = UserSign.builder()
//                .email("pmsu2008")
//                .password("1234")
//                .build();
        // when
//        String result = userService.login(sign);

        // then
  //      System.out.println("result = " + result);
    }

    @Test
    @DisplayName(value = "비밀번호 변경")
    void changePassword() {
        // given
        UserPassword password = UserPassword.builder()
                .newPassword("rtas281430")
                .oldPassword("1234")
                .build();

        // when
        User result = userService.changePassword(2L, password);
        System.out.println("result = " + result);
    }
}
