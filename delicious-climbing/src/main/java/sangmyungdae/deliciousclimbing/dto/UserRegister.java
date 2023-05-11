package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;

import java.time.LocalDateTime;

@Getter
public class UserRegister {
    private String email;
    private String password;
    private LoginType type;
    private String nickname;
    private Gender gender;
    private LocalDateTime birthday;

    @Builder
    public UserRegister(String email, String password, LoginType type, String nickname,
                        Gender gender, LocalDateTime birthday) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
    }

    public TbUser toEntity() {
        return TbUser.builder()
                .email(this.email)
                .password(this.password)
                .type(this.type)
                .nickname(this.nickname)
                .gender(this.gender)
                .birthday(this.birthday)
                .build();
    }

}
