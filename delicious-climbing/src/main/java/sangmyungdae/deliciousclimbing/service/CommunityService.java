package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.config.FileStore;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.dto.community.*;
import sangmyungdae.deliciousclimbing.dto.like.CommunityLikeDto;
import sangmyungdae.deliciousclimbing.repository.*;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final CommunityLikeRepository communityLikeRepository;
    private final FileStore fileStore;

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
    public Post getPostDetail(Long id) {
        TbPost entity = postRepository.findById(id).orElseThrow(); // Optional<TbPost>

        entity.updateHit(entity.getHits() + 1);
        postRepository.save(entity);

        return Post.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public Post createPost(PostDto dto, MultipartFile[] multipartFiles) {

        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();
        List<TbFile> files = fileStore.storeFiles(multipartFiles);

        TbPost post = TbPost.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        for (TbFile file : files) {
            post.addFile(file);
        }

        // Entity to Response DTO
        return Post.builder()
                .entity(postRepository.save(post))
                .build();
    }

    @Transactional
    public Post updatePost(Long id, PostDto dto) {
        TbPost entity = postRepository.findById(id).orElseThrow(); // Optional<TbPost>
        entity.update(dto.getTitle(), dto.getContent()); // dirty checking

        return Post.builder()
                .entity(postRepository.save(entity))
                .build();
    }

    @Transactional
    public void deletePost(Long id) {
        TbPost entity = postRepository.findById(id).orElseThrow();

        for (TbComment comment : entity.getComments()) {
            commentRepository.deleteById(comment.getId());
        }

        for (TbFile file : entity.getFiles()) {
            fileRepository.deleteById(file.getId());
        }

        postRepository.deleteById(id);
    }

    @Transactional
    public Post postLike(Long userId, Long postId) {
        TbUser user = userRepository.findById(userId).orElseThrow();
        TbPost post = postRepository.findById(postId).orElseThrow();
        TbCommunityLike like = communityLikeRepository.findByUserAndPost(user, post).orElse(null);

        // 이미 추천을 눌렀다면
        if (like != null) {
            communityLikeRepository.delete(like);
            post.updateLike(post.getLikes() - 1);
        } else {
            communityLikeRepository.save(TbCommunityLike.builder().post(post).user(user).build());
            post.updateLike(post.getLikes() + 1);
        }

        return Post.builder()
                .entity(postRepository.save(post))
                .build();
    }

    @Transactional
    public Comment createComment(Long postId, CommentDto dto) {
        // Request DTO to Entity
        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();
        TbPost post = postRepository.findById(postId).orElseThrow();

        TbComment entity = commentRepository.save(dto.toEntity(post, user));


        // Entity to Response DTO
        return Comment.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public Comment updateComment(Long id, CommentDto dto) {
        TbComment entity = commentRepository.findById(id).orElseThrow();

        entity.update(dto.getContent());

        return Comment.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

}
