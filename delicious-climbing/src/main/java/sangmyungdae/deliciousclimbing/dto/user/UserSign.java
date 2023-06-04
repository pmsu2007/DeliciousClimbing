package sangmyungdae.deliciousclimbing.dto.user;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSign {
    private String email;
    private String password;

    @Builder
    public UserSign(TbUser entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
