package sangmyungdae.deliciousclimbing.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPassword {
    private String oldPassword;
    private String newPassword;

    @Builder
    public UserPassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
