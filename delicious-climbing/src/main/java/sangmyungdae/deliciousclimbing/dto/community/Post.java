package sangmyungdae.deliciousclimbing.dto.community;

import lombok.*;
import org.springframework.data.domain.Page;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

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
    private int likes;
    private int hits;
    private LocalDateTime createdAt;
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class User {
        private Long id;
        private String author;
        private String imageUrl;

        public User(TbUser user) {
            this.id = user.getId();
            this.author = user.getNickname();
            this.imageUrl = user.getImageUrl();
        }
    }
    @Builder
    public Post(TbPost entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.likes = entity.getLikes();
        this.hits = entity.getHits();
        this.user = new User(entity.getUser());
        this.comments = entity.getComments().stream().map(Comment::new).collect(Collectors.toList());
        this.files = entity.getFiles().stream().map(File::new).collect(Collectors.toList());
    }

    public static Page<Post> toDtoList(Page<TbPost> entities) {
        return entities.map(entity ->
                Post.builder().entity(entity).build());
    }

}