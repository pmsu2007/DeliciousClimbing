package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.domain.enums.ChatRoomType;
import sangmyungdae.deliciousclimbing.dto.chat.ChatRoom;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.service.ChattingService;
import sangmyungdae.deliciousclimbing.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChattingController {

    private final ChattingService chattingService;
    private final UserService userService;

    @GetMapping("/rooms")
    public String chattingListPage(Model model) {
        List<ChatRoom> mateRooms = chattingService.getMateChatRoomList();
        List<ChatRoom> equipmentRooms = chattingService.getEquipmentChatRoomList();

        model.addAttribute("mateRooms", mateRooms);
        model.addAttribute("equipmentRooms", equipmentRooms);

        return "chattingListPage";
    }

    @GetMapping("/room/{type}/{roomId}")
    public String chattingDetailPage(@PathVariable Long roomId,
                                     @PathVariable ChatRoomType type,
                                     Model model) {

        User user = userService.getUser();
        ChatRoom room = chattingService.getChatDetail(type, roomId);

        model.addAttribute("user", user);
        model.addAttribute("room", room);

        return "chattingDetailPage";
    }
}
