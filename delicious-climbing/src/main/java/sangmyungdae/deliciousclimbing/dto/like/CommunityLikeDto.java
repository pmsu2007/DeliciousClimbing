package sangmyungdae.deliciousclimbing.dto.like;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommunityLikeDto {
    private Long userId;
    private Long postId;

    @Builder
    public CommunityLikeDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
