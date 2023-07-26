package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Role;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.service.UserService;
import sangmyungdae.deliciousclimbing.util.FileStore;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final FileStore fileStore;

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

    @GetMapping("/profile")
    public String userProfilePage(UserDto userDto,
                                  UserPassword userPassword,
                                  Model model) {
        User user = userService.getUser();
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
    public String login(@ModelAttribute UserSign dto) {
        User user = userService.login(dto);
        return "redirect:/profile";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegister dto) {
        dto.setRole(Role.USER);
        dto.setType(LoginType.SINGIN);
        userService.createUser(dto);
        return "redirect:/login";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute UserDto dto) {

        userService.updateUser(dto);

        return "redirect:/profile";
    }

    @PostMapping("/user/update/image")
    public String updateUserImage(MultipartFile file) {
        userService.updateUserImage(file);
        return "redirect:/profile";
    }

    @PostMapping("/user/update/password")
    public String changePassword(@ModelAttribute UserPassword dto) {
        // 사용자 Authnetication 확인
        userService.changePassword(dto);
        return "redirect:/profile";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@ModelAttribute UserPassword dto) {
        userService.deleteUser(dto.getOldPassword());
        return "redirect:/login";
    }

    @ResponseBody
    @GetMapping("/user/image/{filename}")
    public Resource getFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
