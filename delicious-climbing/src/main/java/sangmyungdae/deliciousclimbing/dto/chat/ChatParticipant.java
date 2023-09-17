package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Data
public class ChatParticipant {
    private Long id;
    private ChatRoom chatRoom;
    private Creator creator;

    @Data
    public static class ChatRoom {
        private Long id;
        private String roomName;
        private int currentCount;
        private int totalCount;

        public ChatRoom(TbMateChatRoom chatRoom) {
            this.id = chatRoom.getId();
            this.roomName = chatRoom.getRoomName();
            this.currentCount = chatRoom.getCurrentCount();
            this.totalCount = chatRoom.getTotalCount();
        }
    }

    @Data
    public static class Creator {
        private Long id;
        private String nickname;
        private String imageUrl;

        public Creator(TbUser user) {
            this.id = user.getId();
            this.nickname = user.getNickname();
            this.imageUrl = user.getStoreFileName();
        }
    }

    @Builder
    public ChatParticipant(Long id, TbMateChatRoom chatRoom, TbUser creator) {
        this.id = id;
        this.chatRoom = new ChatRoom(chatRoom);
        this.creator = new Creator(creator);
    }
}
