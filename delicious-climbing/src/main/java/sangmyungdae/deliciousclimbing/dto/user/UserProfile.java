package sangmyungdae.deliciousclimbing.dto.user;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Data
public class UserProfile {
    private Long id;
    private String email;
    private String nickname;
    private String imageUrl;

    @Builder
    public UserProfile(TbUser entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.imageUrl = entity.getStoreFileName();
    }
}
