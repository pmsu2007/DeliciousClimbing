package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Role;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String userLoginPage(Model model, UserSign userSign) {
        // UserSign DTO 전달
        model.addAttribute("userSign", userSign);
        return "loginPage";
    }

    @GetMapping("/register")
    public String userRegisterPage(Model model, UserRegister userRegister) {
        // UserRegister DTO 전달
        model.addAttribute("userRegister", userRegister);
        return "registerPage";
    }

    @GetMapping("/find-password")
    public String findPasswordPage() {
        return "passwordFind";
    }

    @GetMapping("/profile/{userId}")
    public String userProfilePage(@PathVariable Long userId,
                                  UserDto userDto,
                                  UserPassword userPassword,
                                  Model model) {
        User user = userService.getUser(userId);
        // 프로필 정보 전달
        model.addAttribute("user", user);
        // 프로필 수정 DTO 전달
        model.addAttribute("userDto", userDto);
        // 비밀번호 변경 & 회원 탈퇴 DTO 전달
        model.addAttribute("userPassword", userPassword);

        return "account";
    }

    @GetMapping("/test")
    public String testPage() {
        return "testPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserSign dto, RedirectAttributes redirect) {
        User user = userService.login(dto);
        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegister dto) {
        if(userService.existEmail(dto.getEmail())) {
           // 예외 처리
        }
        dto.setRole(Role.USER);
        dto.setType(LoginType.SINGIN);
        userService.createUser(dto);
        return "redirect:/login";
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@ModelAttribute UserDto dto,
                             @PathVariable Long userId) {
        // 사용자 Authentication 확인
        User user = userService.updateUser(userId, dto);

        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/update/password/{userId}")
    public String changePassword(@ModelAttribute UserPassword dto,
                                 @PathVariable Long userId) {
        // 사용자 Authnetication 확인
        userService.changePassword(userId, dto);
        return "redirect:/profile/{userId}";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@ModelAttribute UserPassword dto,
                             @PathVariable Long userId) {
        userService.deleteUser(userId, dto);
        return "redirect:/login";
    }
}
