package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChatMessage;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatMessage;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;

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
    public ChatMessage(TbMateChatMessage mate, TbEquipmentChatMessage equipment, ChatRoomType type) {
        if (type.equals(ChatRoomType.MATE)) {
            this.id = mate.getId();
            this.content = mate.getContent();
            this.user = new User(mate.getUser());
            this.createdAt = mate.getCreatedAt();
        } else {
            this.id = equipment.getId();
            this.content = equipment.getContent();
            this.user = new User(equipment.getUser());
            this.createdAt = equipment.getCreatedAt();
        }
    }
}
