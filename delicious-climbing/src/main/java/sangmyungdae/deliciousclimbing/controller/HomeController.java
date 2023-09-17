package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private UserService userService;

    @GetMapping("/")
    public String startPage() {
        return "startPage";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "mainPage";
    }
}
