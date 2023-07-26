package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String startPage() {
        return "startPage";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "mainPage";
    }
}
