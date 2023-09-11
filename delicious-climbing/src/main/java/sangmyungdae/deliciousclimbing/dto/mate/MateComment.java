package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateComment;

import java.time.LocalDateTime;

/**
 * 등산 메이트 댓글 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MateComment {
    private Long id;
    private String content;
    private String updatedAt;

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
        this.userImageUrl = entity.getUser().getStoreFileName();
    }
}
