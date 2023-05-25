package sangmyungdae.deliciousclimbing.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Role;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRegister {
    private String email;
    private String password;
    private Role role;
    private LoginType type;
    private String nickname;
    private Gender gender;
    private LocalDateTime birthday;

    @Builder
    public UserRegister(String email, String password, Role role, LoginType type, String nickname,
                        Gender gender, LocalDateTime birthday) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
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
