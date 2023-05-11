package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPassword {
    private String oldPassword;
    private String newPassword;

    @Builder
    public UserPassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
