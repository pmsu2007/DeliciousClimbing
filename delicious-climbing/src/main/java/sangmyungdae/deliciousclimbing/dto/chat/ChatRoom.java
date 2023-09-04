package sangmyungdae.deliciousclimbing.dto.chat;


import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ChatRoom {

    private Long id;
    private String roomName;
    private int currentCount;
    private int totalCount;
    private List<ChatMessage> messages;

    @Builder
    public ChatRoom(TbChatRoom entity) {
        this.id = entity.getId();
        this.roomName = entity.getRoomName();
        this.currentCount = entity.getCurrentCount();
        this.totalCount = entity.getTotalCount();
        this.messages = entity.getChatMessages().stream()
                .map(ChatMessage::new).collect(Collectors.toList());
    }
}
