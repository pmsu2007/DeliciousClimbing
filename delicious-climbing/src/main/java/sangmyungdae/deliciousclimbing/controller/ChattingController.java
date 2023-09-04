package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;
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
        List<ChatRoom> rooms = chattingService.getChatRoomList();

        model.addAttribute("rooms", rooms);

        return "chattingListPage";
    }

    @GetMapping("/room/{roomId}")
    public String chattingDetailPage(@PathVariable Long roomId,
                                     Model model) {

        User user = userService.getUser();
        ChatRoom room = chattingService.getChatDetail(roomId);

        model.addAttribute("user", user);
        model.addAttribute("room", room);

        return "chattingDetailPage";
    }
}
