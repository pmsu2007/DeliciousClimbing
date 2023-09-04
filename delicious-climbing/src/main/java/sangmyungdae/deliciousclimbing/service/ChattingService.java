package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatMessage;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatParticipant;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.dto.chat.*;
import sangmyungdae.deliciousclimbing.repository.ChatMessageRepository;
import sangmyungdae.deliciousclimbing.repository.ChatParticipantRepository;
import sangmyungdae.deliciousclimbing.repository.ChatRoomRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbChatRoom findChatRoom(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(() -> ExceptionUtil.id(roomId, TbChatRoom.class.getName()));
    }

    public List<ChatRoom> getChatRoomList() {
        TbUser user = findUser(AuthUtil.getAuthUser());

        List<TbChatParticipant> chats = chatParticipantRepository.findByCreator(user);

        List<TbChatRoom> chatRooms = chats.stream()
                .map(TbChatParticipant::getRoom)
                .collect(Collectors.toList());

        return chatRooms.stream().map(ChatRoom::new).collect(Collectors.toList());
    }

    public ChatRoom getChatDetail(Long roomId) {
        TbChatRoom chatRoom = findChatRoom(roomId);

        return ChatRoom.builder()
                .entity(chatRoom)
                .build();
    }

    public void deleteChatRoom(Long roomId) {

    }

    public boolean enterChatRoom(ChatMessageDto dto) {
        TbChatRoom room = findChatRoom(dto.getRoomId());
        TbUser user = findUser(dto.getUsername());

        boolean isEntered = chatParticipantRepository.existsByCreator(user);

        if(!isEntered && room.getCurrentCount() + 1 <= room.getTotalCount()) {
            TbChatParticipant participant = TbChatParticipant.builder()
                    .creator(user)
                    .room(room)
                    .build();

            room.updateCurrentCount(room.getCurrentCount() + 1);
            chatRoomRepository.save(room);

            chatParticipantRepository.save(participant);
        }

        return isEntered;
    }

    public void leaveChatRoom(ChatMessageDto dto) {
        TbChatRoom room = findChatRoom(dto.getRoomId());
        TbUser user = findUser(dto.getUsername());

        TbChatParticipant participant = chatParticipantRepository.findByCreatorAndRoom(user, room);

        room.updateCurrentCount(room.getCurrentCount() - 1);
        chatRoomRepository.save(room);

        chatParticipantRepository.delete(participant);
    }

    public ChatMessage sendMessage(ChatMessageDto dto) {
        TbUser user = findUser(dto.getUsername());
        TbChatRoom chatRoom = findChatRoom(dto.getRoomId());

        TbChatMessage message = TbChatMessage.builder()
                .room(chatRoom)
                .user(user)
                .content(dto.getContent())
                .build();

        return ChatMessage.builder()
                .entity(chatMessageRepository.save(message))
                .build();
    }
}
