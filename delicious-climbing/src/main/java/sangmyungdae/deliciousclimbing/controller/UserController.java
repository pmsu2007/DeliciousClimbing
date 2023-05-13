package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String userLoginPage() {
        return "loginPage";
    }

    @GetMapping("/register")
    public String userRegisterPage() {
        return "registerPage";
    }

    @GetMapping("/find-password")
    public String findPasswordPage() {
        return "passwordFind";
    }

    @GetMapping("/profile/{userId}")
    public String userProfilePage(@PathVariable Long userId,
                                  Model model) {
        User user = userService.getUser(userId);
        model.addAttribute(user);

        return "account";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserSign dto) {
        userService.login(dto);

        return "redirect:/main";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegister dto) {
        if(userService.existEmail(dto.getEmail())) {
           // 예외 처리
        }
        userService.createUser(dto);
        return "redirect:/login";
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@ModelAttribute UserDto dto,
                             @PathVariable Long userId) {
        // 사용자 Authentication 확인
        userService.updateUser(userId, dto);
        return "redirect:/profile/{userId}";
    }

    @PostMapping("/update/password/{userId}")
    public String changePassword(@ModelAttribute UserPassword dto,
                                 @PathVariable Long userId) {
        // 사용자 Authnetication 확인
        userService.changePassword(userId, dto);
        return "redirect:/profile/{userId}";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/";
    }
}
