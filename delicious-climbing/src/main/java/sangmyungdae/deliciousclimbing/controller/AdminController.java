package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.dto.user.UserDto;
import sangmyungdae.deliciousclimbing.dto.user.UserRegister;
import sangmyungdae.deliciousclimbing.dto.user.UserSign;
import sangmyungdae.deliciousclimbing.service.AdminService;
import sangmyungdae.deliciousclimbing.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("/mapping/famous-mountain/address")
    public ResponseEntity<List<Map<String, Object>>> mappingFamousMountainAddress() {
        try {
            return ResponseEntity.ok(adminService.mappingFamousMountainAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/mapping/famous-mountain/region")
    public ResponseEntity<List<Map<String, Object>>> mappingFamousMountainRegion() {
        try {
            return ResponseEntity.ok(adminService.mappingFamousMountainRegion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegister> adminRegister(@RequestBody UserRegister dto) {
        try {
            return ResponseEntity.ok(userService.createUser(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<User> adminLogin(@RequestBody UserSign dto) {
        try {
            return ResponseEntity.ok(userService.login(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
