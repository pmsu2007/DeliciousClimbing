package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.dto.community.File;
import sangmyungdae.deliciousclimbing.dto.community.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class Post {
    private long id;
    private BoardType type;
    private String title;
    private String content;
    private String author;
    private String authorImgUrl;
    private int likes;
    private int hits;
    private LocalDateTime createdAt;
    private List<Comment> comments = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    @Builder
    public Post(TbPost entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.likes = entity.getLikes().size();
        this.hits = entity.getHits();
        this.author = entity.getUser().getNickname();
        this.authorImgUrl = entity.getUser().getImageUrl();
        this.comments = entity.getComments().stream().map(Comment::new).collect(Collectors.toList());
        this.files = entity.getFiles().stream().map(File::new).collect(Collectors.toList());
    }

    public static Page<Post> toDtoList(Page<TbPost> entities) {
        Page<Post> posts = entities.map(entity ->
                Post.builder().entity(entity).build());

        return posts;
    }
}