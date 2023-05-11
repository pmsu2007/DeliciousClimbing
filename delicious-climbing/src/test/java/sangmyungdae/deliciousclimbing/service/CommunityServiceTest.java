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
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.dto.Comment;
import sangmyungdae.deliciousclimbing.dto.CommentDto;
import sangmyungdae.deliciousclimbing.dto.Post;
import sangmyungdae.deliciousclimbing.dto.PostDto;
import sangmyungdae.deliciousclimbing.repository.CommentRepository;
import sangmyungdae.deliciousclimbing.repository.FileRepository;
import sangmyungdae.deliciousclimbing.repository.PostRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;

import java.time.LocalDateTime;

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

    private  PostDto post1 = PostDto.builder()
            .title("테스트1")
            .type(BoardType.FREE)
            .content("테스트1")
            .userId(1L)
            .build();

    private  PostDto post2 = PostDto.builder()
            .title("테스트2")
            .type(BoardType.FREE)
            .content("테스트2")
            .userId(1L)
            .build();

    private PostDto post3 = PostDto.builder()
            .title("테스트3")
            .type(BoardType.AGE)
            .content("테스트3")
            .userId(1L)
            .build();

    
    @Test
    void createPost() {

        // given
        userRepository.save(user);

        // when
        Post response = communityService.createPost(post1);

        // then
        assertThat(response.getTitle()).isEqualTo("테스트1");
        System.out.println("response = " + response);

    }

    @Test
    void getListPost() {
        Pageable pageable = PageRequest.of(0, 10);
        // given
        // when
        userRepository.save(user);
        communityService.createPost(post1);
        communityService.createPost(post2);
        communityService.createPost(post3);
        Page<Post> lists = communityService.getPostList(BoardType.FREE, "테스트", pageable);

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
        communityService.createPost(post1);
        Post result = communityService.getPostDetail(1L);
        // then
        System.out.println("result = " + result);
    }

    @Test
    void updatePost() {

        // given

        // when
        userRepository.save(user);
        Post newPost = communityService.createPost(post1);
        Post changePost = communityService.updatePost(1L, post2);
        Post findPost = communityService.getPostDetail(1L);
        // then
        System.out.println("newPost = " + newPost);
        System.out.println("changePost = " + changePost);
        System.out.println("findPost = " + findPost);
    }

    @Test
    void deletePost() {
        
        userRepository.save(user);
        Post newPost = communityService.createPost(post1);
        communityService.deletePost(1L);

        Post findPost = communityService.getPostDetail(1L);
        // 오류 발생하면 제대로 지워진 것.
        System.out.println("findPost = " + findPost);
        
    }

    @Test
    void createComment() {
        
        // given
        userRepository.save(user);
        communityService.createPost(post1);

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