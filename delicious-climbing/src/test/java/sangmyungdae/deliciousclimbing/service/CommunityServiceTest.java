//package sangmyungdae.deliciousclimbing.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
//import sangmyungdae.deliciousclimbing.dto.community.*;
//import sangmyungdae.deliciousclimbing.dto.like.CommunityLikeDto;
//import sangmyungdae.deliciousclimbing.repository.*;
//
//@SpringBootTest
//public class CommunityServiceTest {
//
//    @Autowired
//    private CommunityService communityService;
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private FileRepository fileRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private CommunityLikeRepository communityLikeRepository;
//
//    @Test
//    @DisplayName(value = "게시글 등록")
//    void createPost() {
//        // given
//        PostDto requestPost = PostDto.builder()
//                .type(BoardType.FREE)
//                .title("제목6")
//                .content("내용6")
//                .userId(33L)
//                .build();
//
//        // when
//        Post post = communityService.createPost(requestPost);
//
//        // then
//        System.out.println("post = " + post);
//    }
//
//    @Test
//    @DisplayName(value = "게시글 수정")
//    void updatePost() {
//        // given
//        PostDto requestPost = PostDto.builder()
//                .type(BoardType.FREE)
//                .title("변경된 제목1")
//                .content("변경된 내용1")
//                .userId(1L)
//                .build();
//        // when
//        Post post = communityService.updatePost(1L, requestPost);
//        // then
//        System.out.println("post = " + post);
//    }
//
//    @Test
//    @DisplayName(value = "게시글 삭제")
//    void deletePost() {
//        communityService.deletePost(1L);
//    }
//
//    @Test
//    @DisplayName(value = "댓글 등록")
//    void createComment() {
//        // given
//        CommentDto requestComment = CommentDto.builder()
//                .postId(1L)
//                .userId(35L)
//                .content("댓글")
//                .build();
//
//        // when
//        Comment comment = communityService.createComment(requestComment);
//
//        // then
//        System.out.println("comment = " + comment);
//    }
//
//    @Test
//    @DisplayName(value = "댓글 수정")
//    void updateComment() {
//        // given
//        CommentDto requestComment = CommentDto.builder()
//                .postId(1L)
//                .userId(1L)
//                .content("변경된 댓글")
//                .build();
//
//        // when
//        Comment comment = communityService.updateComment(1L, requestComment);
//
//        // then
//        System.out.println("comment = " + comment);
//    }
//
//    @Test
//    @DisplayName(value = "댓글 삭제")
//    void deleteComment() {
//        communityService.deleteComment(1L);
//    }
//
//
//    @Test
//    @DisplayName(value = "게시글 상세 조회")
//    void getPostDetail() {
//        // given
//
//        // when
//        Post post = communityService.getPostDetail(1L);
//        // then
//        System.out.println("post = " + post);
//    }
//
//    @Test
//    @DisplayName(value = "게시글 목록 조회")
//    void getPostList() {
//        // given
//        PostSearchDto all = PostSearchDto.builder().build();
//        PostSearchDto type = PostSearchDto.builder().type(BoardType.FREE).build();
//        PostSearchDto titleAndType = PostSearchDto.builder().type(BoardType.FREE).title("제목").build();
//        Pageable pageable = PageRequest.of(0, 5);
//        // when
//
//        Page<Post> posts = communityService.getPostList(titleAndType, pageable);
//
//        // then
//        for (Post post : posts) {
//            System.out.println("post = " + post);
//        }
//    }
//
//    @Test
//    @DisplayName(value = "게시글 추천")
//    void likePost() {
//        // given
//        CommunityLikeDto requestLike = CommunityLikeDto.builder()
//                .postId(1L)
//                .userId(1L)
//                .build();
//        // when
//
//        communityService.postLike(requestLike);
//
//        // then
//
//        Post post = communityService.getPostDetail(1L);
//        System.out.println("post = " + post);
//    }
//}