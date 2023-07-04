package sangmyungdae.deliciousclimbing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;
import sangmyungdae.deliciousclimbing.domain.enums.*;
import sangmyungdae.deliciousclimbing.dto.mate.*;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.dto.user.UserRegister;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.repository.MountainRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MateServiceTest2 {

    @Autowired
    UserService userService;
    @Autowired
    MateService mateService;
    @Autowired
    MountainRepository mountainRepository;

    @Test
    void createPost() {
        User user = userService.createUser(UserRegister.builder()
                .role(Role.USER)
                .email("pmsu2008")
                .password("1234")
                .type(LoginType.SINGIN)
                .nickname("민스님")
                .gender(Gender.MALE)
                .birthday(LocalDateTime.now()).build());

        TbMountain mt = mountainRepository.save(TbMountain.builder().name("mt").build());
        Mate mate = mateService.createPost(user.getId(), MateDto.builder()
                .content("testContent")
                .title("testTitle")
                .recruitDate(LocalDateTime.now())
                .recruitCount(3)
                .recruitStatus(true)
                .mountainId(mt.getId())
                .build());

        MateComment comment = mateService.createComment(mate.getId(), user.getId(), MateCommentDto.builder().content("testComment").build());
        //mateService.deleteComment(mate.getId(), comment.getId());
        MatePost postDetail = mateService.getPostDetail(mate.getId());
        System.out.println("postDetail = " + postDetail);


    }
}