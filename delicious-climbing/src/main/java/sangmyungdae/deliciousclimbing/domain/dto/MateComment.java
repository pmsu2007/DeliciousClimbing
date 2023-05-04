package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String imageUrl;
    private String nickname;

    @Builder
    public MateComment(Long id, String content, LocalDateTime updatedAt,
                       Long userId, String imageUrl, String nickname) {
        this.id = id;
        this.content = content;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
    }
}
