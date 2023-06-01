package sangmyungdae.deliciousclimbing.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSign {
    private String email;
    private String password;

    @Builder
    public UserSign(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
