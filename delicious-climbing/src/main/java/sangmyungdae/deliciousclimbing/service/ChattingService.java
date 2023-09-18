package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;
import sangmyungdae.deliciousclimbing.dto.chat.*;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final MateChatRoomRepository mateChatRoomRepository;
    private final MateChatRepository mateChatRepository;
    private final MateChatMessageRepository mateChatMessageRepository;

    private final EquipmentChatRoomRepository equipmentChatRoomRepository;
    private final EquipmentChatRepository equipmentChatRepository;
    private final EquipmentChatMessageRepository equipmentChatMessageRepository;

    private final UserRepository userRepository;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbMateChatRoom findMateChatRoom(Long roomId) {
        return mateChatRoomRepository.findById(roomId)
                .orElseThrow(() -> ExceptionUtil.id(roomId, TbMateChatRoom.class.getName()));
    }

    private TbEquipmentChatRoom findEquipmentChatRoom(Long roomId) {
        return equipmentChatRoomRepository.findById(roomId)
                .orElseThrow(() -> ExceptionUtil.id(roomId, TbMateChatRoom.class.getName()));
    }

    public List<ChatRoom> getMateChatRoomList() {
        TbUser user = findUser(AuthUtil.getAuthUser());

        List<TbMateChat> chats = mateChatRepository.findByParticipant(user);

        List<TbMateChatRoom> chatRooms = chats.stream()
                .map(TbMateChat::getRoom)
                .collect(Collectors.toList());

        return chatRooms.stream().map(mate -> ChatRoom.builder()
                .type(ChatRoomType.MATE)
                .mate(mate)
                .build()).collect(Collectors.toList());
    }

    public List<ChatRoom> getEquipmentChatRoomList() {
        TbUser user = findUser(AuthUtil.getAuthUser());

        List<TbEquipmentChat> chats = equipmentChatRepository.findByCreator(user);

        List<TbEquipmentChatRoom> chatRooms = chats.stream()
                .map(TbEquipmentChat::getRoom)
                .collect(Collectors.toList());

        return chatRooms.stream().map(equipment -> ChatRoom.builder()
                .type(ChatRoomType.EQUIPMENT)
                .equipment(equipment)
                .build()).collect(Collectors.toList());
    }

    public ChatRoom getChatDetail(ChatRoomType type, Long roomId) {

        if (type.equals(ChatRoomType.MATE)) {
            TbMateChatRoom chatRoom = findMateChatRoom(roomId);

            return ChatRoom.builder()
                    .type(type)
                    .mate(chatRoom)
                    .build();
        } else {
            TbEquipmentChatRoom chatRoom = findEquipmentChatRoom(roomId);

            return ChatRoom.builder()
                    .type(type)
                    .equipment(chatRoom)
                    .build();
        }
    }

    public boolean enterChatRoom(ChatMessageDto dto) {
        TbUser user = findUser(dto.getUsername());

        if(dto.getChatRoomType().equals(ChatRoomType.MATE)) {
            TbMateChatRoom room = findMateChatRoom(dto.getRoomId());

            boolean isEntered = mateChatRepository.existsByParticipantAndRoom(user, room);

            if(!isEntered && room.getCurrentCount() + 1 <= room.getTotalCount()) {
                TbMateChat participant = TbMateChat.builder()
                        .participant(user)
                        .room(room)
                        .build();

                room.updateCurrentCount(room.getCurrentCount() + 1);
                mateChatRoomRepository.save(room);

                mateChatRepository.save(participant);
            }

            return isEntered;
        } else {
            TbEquipmentChatRoom room = findEquipmentChatRoom(dto.getRoomId());

            boolean isEntered = equipmentChatRepository.existsByCreatorAndRoom(user, room);

            if(!isEntered && room.getCurrentCount() + 1 <= room.getTotalCount()) {
                TbEquipmentChat participant = TbEquipmentChat.builder()
                        .creator(user)
                        .room(room)
                        .build();

                room.updateCurrentCount(room.getCurrentCount() + 1);
                equipmentChatRoomRepository.save(room);

                equipmentChatRepository.save(participant);
            }

            return isEntered;
        }
    }

    public void leaveChatRoom(ChatMessageDto dto) {

        TbUser user = findUser(dto.getUsername());

        if (dto.getChatRoomType().equals(ChatRoomType.MATE)) {
            TbMateChatRoom room = findMateChatRoom(dto.getRoomId());

            TbMateChat chat = mateChatRepository.findByParticipantAndRoom(user, room);

            room.updateCurrentCount(room.getCurrentCount() - 1);
            mateChatRoomRepository.save(room);

            mateChatRepository.delete(chat);
        } else {
            TbEquipmentChatRoom room = findEquipmentChatRoom(dto.getRoomId());

            TbEquipmentChat participant = equipmentChatRepository.findByCreatorAndRoom(user, room);

            room.updateCurrentCount(room.getCurrentCount() - 1);
            equipmentChatRoomRepository.save(room);

            equipmentChatRepository.delete(participant);
        }
    }

    public ChatMessage sendMessage(ChatMessageDto dto) {
        TbUser user = findUser(dto.getUsername());

        if(dto.getChatRoomType().equals(ChatRoomType.MATE)) {
            TbMateChatRoom chatRoom = findMateChatRoom(dto.getRoomId());

            TbMateChatMessage message = TbMateChatMessage.builder()
                    .room(chatRoom)
                    .user(user)
                    .content(dto.getContent())
                    .build();

            return ChatMessage.builder()
                    .type(dto.getChatRoomType())
                    .mate(mateChatMessageRepository.save(message))
                    .build();
        } else {
            TbEquipmentChatRoom chatRoom = findEquipmentChatRoom(dto.getRoomId());

            TbEquipmentChatMessage message = TbEquipmentChatMessage.builder()
                    .room(chatRoom)
                    .user(user)
                    .content(dto.getContent())
                    .build();

            return ChatMessage.builder()
                    .type(dto.getChatRoomType())
                    .equipment(equipmentChatMessageRepository.save(message))
                    .build();
        }
    }
}
