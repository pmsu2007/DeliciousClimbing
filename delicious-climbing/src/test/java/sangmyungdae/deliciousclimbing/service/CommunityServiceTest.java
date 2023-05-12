package sangmyungdae.deliciousclimbing.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.repository.CommentRepository;
import sangmyungdae.deliciousclimbing.repository.FileRepository;
import sangmyungdae.deliciousclimbing.repository.PostRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@DataJpaTest
class CommunityServiceTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;

    CommunityService communityService;

    @BeforeEach
    public void beforeEach() {
        communityService = new CommunityService(postRepository
                , commentRepository
                , fileRepository
                , userRepository);
    }

    private TbUser user = TbUser.builder()
            .type(LoginType.SINGIN)
            .email("테스트")
            .password("1234")
            .nickname("민스님")
            .gender(Gender.MALE)
            .birthday(LocalDateTime.now())
            .build();

    @Test
    void getListPost() {
        // given
        userRepository.save(user);
        Pageable pageable = PageRequest.of(0, 10);

        List<TbPost> posts = IntStream.range(1, 12).mapToObj(i ->
                TbPost.builder()
                        .user(user)
                        .type(BoardType.FREE)
                        .title("제목 : " + i)
                        .content("내용 : " + i)
                        .hits(i)
                        .build()).collect(Collectors.toList());

        postRepository.saveAll(posts);

        // when
        Page<Post> lists = communityService.getPostList(PostSearchDto.builder().build(), pageable);

        // then
        for (Post post : lists.getContent()) {
            System.out.println("post = " + post);
        }
    }

    @Test
    void getPostDetail() {
        // given
        
        // when
        userRepository.save(user);
        // communityService.createPost(post1);

        Post result = communityService.getPostDetail(1L);
        // then
        System.out.println("result = " + result);
    }

    @Test
    void updatePost() {

        // given

        // when
        userRepository.save(user);
        // Post newPost = communityService.createPost(post1);
        // Post changePost = communityService.updatePost(1L, post2);
        Post findPost = communityService.getPostDetail(1L);
        // then
        // System.out.println("newPost = " + newPost);
        // System.out.println("changePost = " + changePost);
        System.out.println("findPost = " + findPost);
    }

    @Test
    void deletePost() {
        
        userRepository.save(user);
        // Post newPost = communityService.createPost(post1);

        communityService.deletePost(1L);

        Post findPost = communityService.getPostDetail(1L);
        // 오류 발생하면 제대로 지워진 것.
        System.out.println("findPost = " + findPost);
        
    }

    @Test
    void createComment() {
        
        // given
        userRepository.save(user);
        //  communityService.createPost(post1);


        // DTO에 Entity를 담아도 되나 ?
        CommentDto comment1 = CommentDto.builder()
                .content("댓글1")
                .build();
        
        // when
        Comment comment = communityService.createComment(comment1);

        // then
        System.out.println("comment = " + comment);
    }
}