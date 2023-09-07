package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatMessage;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Data
public class ChatMessage {
    private Long id;
    private String content;
    private User user;
    private String createdAt;

    @Data
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
    public ChatMessage(TbChatMessage entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = new User(entity.getUser());
        this.createdAt = entity.getCreatedAt();
    }
}
