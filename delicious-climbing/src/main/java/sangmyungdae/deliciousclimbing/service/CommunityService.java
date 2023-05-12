package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbComment;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.repository.CommentRepository;
import sangmyungdae.deliciousclimbing.repository.FileRepository;
import sangmyungdae.deliciousclimbing.repository.PostRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    // 추천 레포지토리 추가 예상...

    @Transactional
    public Page<Post> getPostList(PostSearchDto dto, Pageable pageable) {

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

        return Post.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public Post createPost(PostDto dto) {

        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();
        // Request DTO to Entity
        TbPost entity = postRepository.save(dto.toEntity(user));

        // Entity to Response DTO
        return Post.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public Post updatePost(Long id, PostDto dto) {
        TbPost entity = postRepository.findById(id).orElseThrow(); // Optional<TbPost>
        entity.update(dto.getTitle(), dto.getContent()); // dirty checking
        return Post.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Comment createComment(CommentDto dto) {
        // Request DTO to Entity
        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();
        TbPost post = postRepository.findById(dto.getPostId()).orElseThrow();

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
    public File createFile(FileDto dto) {
        TbPost post = postRepository.findById(dto.getPostId()).orElseThrow();

        // Request DTO to Entity
        TbFile entity = fileRepository.save(dto.toEntity(post));

        // Entity to Response DTO
        return File.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public File updateFile(Long id, FileDto dto) {

        TbFile entity = fileRepository.findById(id).orElseThrow();
        entity.update(dto.getFileName());
        return File.builder()
                .entity(entity)
                .build();

    }

    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

}
