package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.*;

/**
 * 등산 메이트  게시글(+ 댓글 + 파일) Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatePost {

    private Mate mate;
    private MateComment comment;
    private MateFile file;

    @Builder
    public MatePost(Mate mate, MateComment comment, MateFile file) {
        this.mate = mate;
        this.comment = comment;
        this.file = file;
    }
}
