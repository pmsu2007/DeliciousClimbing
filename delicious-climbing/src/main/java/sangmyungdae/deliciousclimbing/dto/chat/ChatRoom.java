package sangmyungdae.deliciousclimbing.dto.chat;


import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatRoom;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ChatRoom {

    private Long id;
    private ChatRoomType type;
    private String roomName;
    private int currentCount;
    private int totalCount;
    private List<ChatMessage> messages;

    @Builder
    public ChatRoom(TbMateChatRoom mate, TbEquipmentChatRoom equipment, ChatRoomType type) {
        if (type.equals(ChatRoomType.MATE)) {
            this.id = mate.getId();
            this.type = type;
            this.roomName = mate.getRoomName();
            this.currentCount = mate.getCurrentCount();
            this.totalCount = mate.getTotalCount();
            this.messages = mate.getMessages().stream()
                    .map(message -> ChatMessage.builder()
                            .type(type)
                            .mate(message)
                            .build()).collect(Collectors.toList());
        } else {
            this.id = equipment.getId();
            this.type = type;
            this.roomName = equipment.getRoomName();
            this.currentCount = equipment.getCurrentCount();
            this.totalCount = equipment.getTotalCount();
            this.messages = equipment.getMessages().stream()
                    .map(message -> ChatMessage.builder()
                            .type(type)
                            .equipment(message)
                            .build()).collect(Collectors.toList());
        }
    }


}
