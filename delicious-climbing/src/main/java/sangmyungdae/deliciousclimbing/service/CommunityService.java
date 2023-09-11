package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.util.FileStore;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.dto.community.*;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final CommunityLikeRepository communityLikeRepository;
    private final FileStore fileStore;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbPost findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> ExceptionUtil.id(id, TbPost.class.getName()));
    }

    private TbComment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> ExceptionUtil.id(id, TbComment.class.getName()));
    }

    @Transactional
    public Page<Post> getPostList(PostSearchDto dto, Pageable pageable) {


        if (dto.getSort() != null && dto.getSort().equals("hits")) {
            pageable = PageRequest.of(0, 10, Sort.by("hits").descending());
        } else if (dto.getSort() != null && dto.getSort().equals("likes")) {
            pageable = PageRequest.of(0, 10, Sort.by("likes").descending());
        }

        if (dto.getType() == null && dto.getTitle() == null) {
            return Post.toDtoList(postRepository.findAll(pageable));
        } else if (dto.getType() != null && dto.getTitle() == null) {
            return Post.toDtoList(postRepository.findPageByType(dto.getType(), pageable));
        } else if (dto.getType() != null && dto.getTitle() != null) {
            return Post.toDtoList(postRepository.findPageByTypeAndTitleContaining(dto.getType(), dto.getTitle(), pageable));
        }

        return null;
    }

    @Transactional
    public Page<Post> getMyPostList(Long userId, Pageable pageable) {
        Page<TbPost> entities = postRepository.findPageByUser_Id(userId, pageable);
        return Post.toDtoList(entities);
    }

    @Transactional
    public Post getPostDetail(Long postId) {
        TbPost post = findPost(postId);

        // 데이터 조회 시, 조회수 증가
        post.updateHit(post.getHits() + 1);

        return Post.builder().entity(postRepository.save(post)).build();
    }

    @Transactional
    public Post createPost(PostDto dto, MultipartFile[] multipartFiles) {

        TbUser user = findUser(AuthUtil.getAuthUser());
        List<TbFile> files = fileStore.storeFiles(multipartFiles).stream()
                .map(file -> TbFile.builder()
                                .storeFileName(file.getStoreFileName())
                                .uploadFileName(file.getUploadFileName())
                                .build())
                .collect(Collectors.toList());

        TbPost post = TbPost.builder().type(dto.getType()).title(dto.getTitle()).content(dto.getContent()).user(user).build();

        for (TbFile file : files) {
            post.addFile(file);
        }

        // Entity to Response DTO
        return Post.builder().entity(postRepository.save(post)).build();
    }

    @Transactional
    public Post updatePost(Long postId, PostDto dto) {
        TbPost post = findPost(postId);

        if (!post.getUser().getNickname().equals(AuthUtil.getAuthUser())) {
            throw ExceptionUtil.available("Forbidden");
        }

        post.update(dto.getTitle(), dto.getContent()); // dirty checking

        return Post.builder().entity(postRepository.save(post)).build();
    }

    @Transactional
    public void deletePost(Long postId) {
        TbPost post = findPost(postId);

        if (!post.getUser().getNickname().equals(AuthUtil.getAuthUser())) {
            throw ExceptionUtil.available("Forbidden");
        }

        commentRepository.deleteAll(post.getComments());
        fileRepository.deleteAll(post.getFiles());
        postRepository.delete(post);
    }

    @Transactional
    public Post postLike(Long postId) {
        TbUser user = findUser(AuthUtil.getAuthUser());
        TbPost post = findPost(postId);

        TbCommunityLike like = communityLikeRepository.findByUserAndPost(user, post).orElse(null);

        // 이미 추천을 눌렀다면
        if (like != null) {
            communityLikeRepository.delete(like);
            post.updateLike(post.getLikes() - 1);
        } else {
            communityLikeRepository.save(TbCommunityLike.builder().post(post).user(user).build());
            post.updateLike(post.getLikes() + 1);
        }

        return Post.builder().entity(postRepository.save(post)).build();
    }

    @Transactional
    public Comment createComment(Long postId, CommentDto dto) {
        // Request DTO to Entity
        TbUser user = findUser(AuthUtil.getAuthUser());
        TbPost post = findPost(postId);

        TbComment entity = commentRepository.save(dto.toEntity(post, user));


        // Entity to Response DTO
        return Comment.builder().entity(entity).build();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        TbComment comment = findComment(commentId);

        if (!comment.getUser().getNickname().equals(AuthUtil.getAuthUser())) {
            throw ExceptionUtil.available("Forbidden");
        }

        commentRepository.delete(comment);
    }
}
