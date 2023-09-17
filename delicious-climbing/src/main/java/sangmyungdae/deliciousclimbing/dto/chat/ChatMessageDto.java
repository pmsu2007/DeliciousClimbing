package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;
import sangmyungdae.deliciousclimbing.domain.enums.MessageType;

@Data
public class ChatMessageDto {
    private Long roomId;
    private ChatRoomType chatRoomType;
    private MessageType messageType;
    private String content;
    private String username;

    public ChatMessageDto(Long roomId, ChatRoomType chatRoomType, MessageType messageType, String content, String username) {
        this.roomId = roomId;
        this.chatRoomType = chatRoomType;
        this.messageType = messageType;
        this.content = content;
        this.username = username;
    }
}
