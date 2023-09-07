package sangmyungdae.deliciousclimbing.dto.chat;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Data
public class ChatRoomDto {
    private String roomName;
    private int currentCount;
    private int totalCount;

    @Builder
    public ChatRoomDto(String roomName, int currentCount, int totalCount) {
        this.roomName = roomName;
        this.currentCount = currentCount;
        this.totalCount = totalCount;
    }

    public TbChatRoom toEntity(TbUser creator) {
        return TbChatRoom.builder()
                .roomName(this.roomName)
                .currentCount(this.currentCount)
                .totalCount(this.totalCount)
                .creator(creator)
                .build();
    }
}
