package sangmyungdae.deliciousclimbing.domain.dto.mate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateComment;

import java.time.LocalDateTime;

/**
 * 등산 메이트 댓글 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateComment {
    private Long id;
    private String content;
    private LocalDateTime updatedAt;

    private Long userId;
    private String nickName;
    private String userImageUrl;

    @Builder
    public MateComment(TbMateComment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.updatedAt = entity.getUpdatedAt();
        this.userId = entity.getUser().getId();
        this.nickName = entity.getUser().getNickname();
        this.userImageUrl = entity.getUser().getImageUrl();
    }
}
