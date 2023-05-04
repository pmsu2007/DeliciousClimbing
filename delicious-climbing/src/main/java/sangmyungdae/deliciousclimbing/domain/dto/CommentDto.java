package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 등산 메이트 댓글 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {

    private Long id;
    private String content;
    private Long userId;

    @Builder
    public CommentDto(Long id, String content, Long userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }
}
