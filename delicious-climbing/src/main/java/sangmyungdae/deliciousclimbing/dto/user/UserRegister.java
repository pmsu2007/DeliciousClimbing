package sangmyungdae.deliciousclimbing.dto.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Role;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {
    private String email;
    private String password;
    private Role role;
    private LoginType type;
    private String nickname;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    @Builder
    public UserRegister(TbUser entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.type = entity.getType();
        this.nickname = entity.getNickname();
        this.gender = entity.getGender();
        this.birthday = entity.getBirthday();
        this.role = entity.getRole();
    }

    public TbUser toEntity() {
        return TbUser.builder()
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .type(this.type)
                .nickname(this.nickname)
                .gender(this.gender)
                .birthday(this.birthday)
                .build();
    }

}
