package sangmyungdae.deliciousclimbing.dto.community;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbComment;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter
@ToString
public class Comment {
    private long id;
    private String content;
    private User user;

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
            this.imageUrl = user.getStoreFileName();
        }
    }

    @Builder
    public Comment(TbComment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = new User(entity.getUser());
    }
}
