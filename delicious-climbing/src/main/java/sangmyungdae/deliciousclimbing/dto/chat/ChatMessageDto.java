package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.enums.MessageType;

@Data
public class ChatMessageDto {
    private Long roomId;
    private MessageType type;
    private String content;
    private String username;

    @Builder
    public ChatMessageDto(Long roomId, MessageType type, String content, String username) {
        this.roomId = roomId;
        this.type = type;
        this.content = content;
        this.username = username;
    }
}
