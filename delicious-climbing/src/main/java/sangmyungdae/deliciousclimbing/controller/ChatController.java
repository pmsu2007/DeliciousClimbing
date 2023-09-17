package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sangmyungdae.deliciousclimbing.domain.enums.MessageType;
import sangmyungdae.deliciousclimbing.dto.chat.ChatMessage;
import sangmyungdae.deliciousclimbing.dto.chat.ChatMessageDto;
import sangmyungdae.deliciousclimbing.service.ChattingService;

@Controller
@RequiredArgsConstructor
public class ChatController {
    // applicationDestinationPrefixes + @MessageMapping
    private final SimpMessagingTemplate template;
    private final ChattingService chattingService;

    // /pub/chat/message 로 날린 메세지에 대해서
    // /sub/chat/message/{roomId} 에 구독한 Client에게 해당 message를 전달
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto dto) {
        if(dto.getMessageType().equals(MessageType.ENTER)) {
            // 처음 채팅방에 들어갔다면
            if(!chattingService.enterChatRoom(dto)) {
                dto.setContent(dto.getUsername() + "님이 입장하셨습니다.");
                ChatMessage message = chattingService.sendMessage(dto);
                template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), message);
            }
        } else if (dto.getMessageType().equals(MessageType.LEAVE)) {
            dto.setContent(dto.getUsername() + "님이 퇴장하셨습니다.");
            chattingService.leaveChatRoom(dto);
            ChatMessage message = chattingService.sendMessage(dto);
            template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), message);
        } else  {
            ChatMessage message = chattingService.sendMessage(dto);
            template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), message);
        }


    }
}
