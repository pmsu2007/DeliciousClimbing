//package sangmyungdae.deliciousclimbing.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import sangmyungdae.deliciousclimbing.domain.enums.Role;
//import sangmyungdae.deliciousclimbing.dto.mate.*;
//import sangmyungdae.deliciousclimbing.domain.entity.*;
//import sangmyungdae.deliciousclimbing.domain.enums.Gender;
//import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
//import sangmyungdae.deliciousclimbing.dto.mate.MateSearchDto;
//import sangmyungdae.deliciousclimbing.dto.user.User;
//import sangmyungdae.deliciousclimbing.dto.user.UserRegister;
//import sangmyungdae.deliciousclimbing.repository.*;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Slf4j
//@SpringBootTest
//class MateServiceTest {
//
//    @Autowired
//    MateService mateService;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    MountainRepository mountainRepository;
//    @Autowired
//    MateRepository mateRepository;
//    @Autowired
//    MateCommentRepository mateCommentRepository;
//    @Autowired
//    MateFileRepository mateFileRepository;
//    @Autowired
//    UserService userService;
//
//    @AfterEach
//    void deleteRepository() {
//        mateCommentRepository.deleteAll();
//        mateFileRepository.deleteAll();
//        mateRepository.deleteAll();
//        mountainRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void getMateList() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain1 = TbMountain.builder()
//                .name("testMountain1")
//                .build();
//        mountainRepository.save(mountain1);
//        TbMountain mountain2 = TbMountain.builder()
//                .name("testMountain2")
//                .build();
//        mountainRepository.save(mountain2);
//        TbMountain mountain3 = TbMountain.builder()
//                .name("testMountain3")
//                .build();
//        mountainRepository.save(mountain3);
//
//        Mate mate1 = mateService.createPost(user1.getId(), MateDto.builder()
//                .content("testContent1")
//                .mountainId(mountain1.getId())
//                .title("testTitle1")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(true)
//                .build());
//        mateService.createComment(mate1.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        mateService.createComment(mate1.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        Mate mate2 = mateService.createPost(user1.getId(), MateDto.builder()
//                .content("testContent2")
//                .mountainId(mountain2.getId())
//                .title("testTitle2")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(true)
//                .build());
//        mateService.createComment(mate2.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        mateService.createComment(mate2.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        Mate mate3 = mateService.createPost(user2.getId(), MateDto.builder()
//                .content("testContent3")
//                .mountainId(mountain1.getId())
//                .title("testTitle3")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(false)
//                .build());
//        mateService.createComment(mate3.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        mateService.createComment(mate3.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        Mate mate4 = mateService.createPost(user1.getId(), MateDto.builder()
//                .content("testContent4")
//                .mountainId(mountain3.getId())
//                .title("testTitle4")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(true)
//                .build());
//        mateService.createComment(mate4.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        mateService.createComment(mate4.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        Mate mate5 = mateService.createPost(user2.getId(), MateDto.builder()
//                .content("testContent5")
//                .mountainId(mountain3.getId())
//                .title("testTitle5")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(false)
//                .build());
//        mateService.createComment(mate5.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        mateService.createComment(mate5.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        //when
//        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "createdAt");
//        // mountainId == null, filtering == false
//        MateSearchDto dto1 = MateSearchDto.builder().recruitStatusFiltering(false).mountainId(null).build();
//        Page<MatePost> mateList1 = mateService.getMateList(dto1, pageable);
//
//        // mountainId == null, filtering == true
//        MateSearchDto dto2 = MateSearchDto.builder().recruitStatusFiltering(true).mountainId(null).build();
//        Page<MatePost> mateList2 = mateService.getMateList(dto2, pageable);
//
//        // mountainId != null, filtering == false
//        MateSearchDto dto3 = MateSearchDto.builder().recruitStatusFiltering(false).mountainId(mountain3.getId()).build();
//        Page<MatePost> mateList3 = mateService.getMateList(dto3, pageable);
//
//        // mountainId != null, filtering == true
//        MateSearchDto dto4 = MateSearchDto.builder().recruitStatusFiltering(true).mountainId(mountain3.getId()).build();
//        Page<MatePost> mateList4 = mateService.getMateList(dto4, pageable);
//
//        //then
//        assertThat(5).isEqualTo(mateList1.getTotalElements());
//        assertThat(3).isEqualTo(mateList2.getTotalElements());
//        assertThat(2).isEqualTo(mateList3.getTotalElements());
//        assertThat(1).isEqualTo(mateList4.getTotalElements());
//
//    }
//
//    @Test
//    void getPostDetail() {
//        //given
//        User user1 = userService.createUser(UserRegister.builder()
//                .role(Role.USER)
//                .email("user1")
//                .password("1234")
//                .type(LoginType.SINGIN)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now()).build());
//        User user2 = userService.createUser(UserRegister.builder()
//                .role(Role.USER)
//                .email("user2")
//                .password("1234")
//                .type(LoginType.SINGIN)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now()).build());
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        Mate mate = mateService.createPost(user1.getId(), MateDto.builder()
//                .content("testContent")
//                .title("testTitle")
//                .recruitDate(LocalDateTime.now())
//                .recruitCount(3)
//                .recruitStatus(true)
//                .mountainId(mountain.getId())
//                .build());
//
//        MateFile file1 = mateService.createFile(mate.getId(), MateFileDto.builder().fileName("testFile1").build());
//        MateFile file2 = mateService.createFile(mate.getId(), MateFileDto.builder().fileName("testFile2").build());
//
//        MateComment comment1 = mateService.createComment(mate.getId(), user1.getId(), MateCommentDto.builder().content("testComment1").build());
//        MateComment comment2 = mateService.createComment(mate.getId(), user2.getId(), MateCommentDto.builder().content("testComment2").build());
//
//        //when
//        MatePost result = mateService.getPostDetail(mate.getId());
////        TbMate tbMate = mateRepository.findById(mate.getId()).get();
//
//        //then
//        assertThat(2).isEqualTo(result.getFiles().size());
//        assertThat(2).isEqualTo(result.getComments().size());
//        assertThat(mate.getTitle()).isEqualTo(result.getMate().getTitle());
//        assertThat(mate.getContent()).isEqualTo(result.getMate().getContent());
//        assertThat(mate.getId()).isEqualTo(result.getMate().getId());
//        assertThat(mate.getHits()).isEqualTo(result.getMate().getHits());
//        assertThat(mate.getMountainId()).isEqualTo(result.getMate().getMountainId());
//        assertThat(mate.getRecruitCount()).isEqualTo(result.getMate().getRecruitCount());
//        assertThat(mate.getRecruitDate()).isEqualToIgnoringNanos(result.getMate().getRecruitDate());
//        assertThat(mate.getNickName()).isEqualTo(result.getMate().getNickName());
//        assertThat(mate.getRecruitStatus()).isEqualTo(result.getMate().getRecruitStatus());
//        assertThat(mate.getUpdatedAt()).isEqualToIgnoringNanos(result.getMate().getUpdatedAt());
//        assertThat(mate.getUserId()).isEqualTo(result.getMate().getUserId());
//        assertThat(mate.getMountainName()).isEqualTo(result.getMate().getMountainName());
//    }
//
//    @Test
//    @Transactional
//    void createPost() {
//        //given
//        User user1 = userService.createUser(UserRegister.builder()
//                .role(Role.USER)
//                .email("user1")
//                .password("1234")
//                .type(LoginType.SINGIN)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now()).build());
//        User user2 = userService.createUser(UserRegister.builder()
//                .role(Role.USER)
//                .email("user2")
//                .password("1234")
//                .type(LoginType.SINGIN)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now()).build());
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        //when
//        Mate result = mateService.createPost(user1.getId(), MateDto.builder()
//                .title("mate")
//                .content("test")
//                .recruitCount(4)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .mountainId(mountain.getId())
//                .build());
//        Mate actual = Mate.builder().entity(mateRepository.findById(result.getId()).get()).build();
//
//        //then
//        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
//    }
//
//    @Test
//    void updatePost() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        TbMate mate = TbMate.builder()
//                .title("mate")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .user(user1)
//                .mountain(mountain)
//                .build();
//        mateRepository.save(mate);
//
//        //when
//        MateDto mateDto = MateDto.builder()
//                .title("updateTitle")
//                .content("updateContent")
//                .recruitCount(10)
//                .recruitDate(LocalDateTime.MIN)
//                .recruitStatus(false)
//                .mountainId(mate.getMountain().getId())
//                .build();
//
//        Mate result = mateService.updatePost(mate.getId(), mateDto);
//        //then
//        assertThat("updateTitle").isEqualTo(result.getTitle());
//        assertThat("updateContent").isEqualTo(result.getContent());
//        assertThat(10).isEqualTo(result.getRecruitCount());
//        assertThat(LocalDateTime.MIN).isEqualTo(result.getRecruitDate());
//        assertThat(false).isEqualTo(result.getRecruitStatus());
//    }
//
//    @Test
//    void deletePost() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        Mate mate = mateService.createPost(user1.getId(), MateDto.builder()
//                .content("testContent1")
//                .mountainId(mountain.getId())
//                .title("testTitle1")
//                .recruitCount(3)
//                .recruitDate(LocalDateTime.now())
//                .recruitStatus(true)
//                .build());
//        MateComment comment1 = mateService.createComment(mate.getId(), user1.getId(), MateCommentDto.builder()
//                .content("testComment1")
//                .build());
//        MateComment comment2 = mateService.createComment(mate.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment2")
//                .build());
//
//        //when
//        mateService.deletePost(mate.getId());
//
//        //then
//        assertThat(mateRepository.findById(mate.getId())).isEmpty();
//        assertThat(mateCommentRepository.findById(comment1.getId())).isEmpty();
//        assertThat(mateCommentRepository.findById(comment2.getId())).isEmpty();
//
//    }
//
//    @Test
//    @Transactional
//    void createComment() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .user(user1)
//                .mountain(mountain)
//                .build();
//        mateRepository.save(mate1);
//
//        //when
//        MateComment result = mateService.createComment(mate1.getId(), user2.getId(), MateCommentDto.builder()
//                .content("testComment")
//                .build());
//        MateComment actual = MateComment.builder().entity(mateCommentRepository.findById(result.getId()).get()).build();
//        MatePost postDetail = mateService.getPostDetail(mate1.getId());
//
//        //then
//        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
//    }
//
//    @Test
//    @Transactional
//    void updateComment() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .user(user1)
//                .mountain(mountain)
//                .build();
//        mateRepository.save(mate1);
//
//        MateComment comment = mateService.createComment(mate1.getId(), user2.getId(), MateCommentDto.builder().content("testComment").build());
//
////        TbMateComment comment = TbMateComment.builder()
////                .mate(mate1)
////                .content("testComment")
////                .user(user2)
////                .build();
////        mateCommentRepository.save(comment);
//
//        //when
//        MateComment result = mateService.updateComment(comment.getId(), MateCommentDto.builder()
//                .content("updateComment")
//                .build());
//
//        MateComment actual = MateComment.builder().entity(mateCommentRepository.findById(result.getId()).get()).build();
//
//        //then
//        assertThat("updateComment").isEqualTo(result.getContent());
//        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
//    }
//
//    @Test
//    void deleteComment() {
//        //given
//        TbUser user1 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user1@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user1")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        TbUser user2 = TbUser.builder()
//                .type(LoginType.SINGIN)
//                .email("user2@test.com")
//                .password("1234")
//                .role(Role.USER)
//                .nickname("user2")
//                .gender(Gender.MALE)
//                .birthday(LocalDateTime.now())
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        TbMountain mountain = TbMountain.builder()
//                .name("testMountain")
//                .build();
//        mountainRepository.save(mountain);
//
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .user(user1)
//                .mountain(mountain)
//                .build();
//        mateRepository.save(mate1);
//
//        MateComment comment = mateService.createComment(mate1.getId(), user2.getId(), MateCommentDto.builder().content("testComment").build());
//
////        TbMateComment comment = TbMateComment.builder()
////                .mate(mate1)
////                .content("testComment")
////                .user(user2)
////                .build();
////        mateCommentRepository.save(comment);
//
//        //when
//        mateService.deleteComment(mate1.getId(), comment.getId());
//
//        //then
//        assertThat(mateFileRepository.findById(comment.getId())).isEmpty();
//    }
//
//    @Test
//    @Transactional
//    void createFile() {
//
//        //given
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .build();
//        mateRepository.save(mate1);
//
//        //when
//        MateFile result = mateService.createFile(mate1.getId(), MateFileDto.builder()
//                .fileName("testFile")
//                .build());
//        MateFile actual = MateFile.builder().entity(mateFileRepository.findById(result.getId()).get()).build();
//        //then
//        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
//    }
//
//    @Test
//    void updateFile() {
//        //given
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .build();
//        mateRepository.save(mate1);
//
//        TbMateFile file = TbMateFile.builder()
//                .fileName("testFile")
//                .mate(mate1)
//                .build();
//        mateFileRepository.save(file);
//
//        //when
//        MateFile result = mateService.updateFile(file.getId(), MateFileDto.builder()
//                .fileName("updateFile")
//                .build());
//        MateFile actual = MateFile.builder().entity(mateFileRepository.findById(file.getId()).get()).build();
//
//        //then
//        assertThat("updateFile").isEqualTo(result.getFileName());
//        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
//
//    }
//
//    @Test
//    void deleteFile() {
//        //given
//        TbMate mate1 = TbMate.builder()
//                .title("mate1")
//                .content("test")
//                .recruitCount(3)
//                .recruitStatus(true)
//                .recruitDate(LocalDateTime.now())
//                .build();
//        mateRepository.save(mate1);
//
//        MateFile file = mateService.createFile(mate1.getId(), MateFileDto.builder().fileName("testFile").build());
//
////        TbMateFile file = TbMateFile.builder()
////                .fileName("testFile")
////                .mate(mate1)
////                .build();
////        mateFileRepository.save(file);
//
//        //when
//        mateService.deleteFile(mate1.getId(), file.getId());
//
//        //then
//        assertThat(mateFileRepository.findById(file.getId())).isEmpty();
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
