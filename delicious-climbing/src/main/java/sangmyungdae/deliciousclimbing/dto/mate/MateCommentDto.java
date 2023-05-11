package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateComment;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

/**
 * 등산 메이트 댓글 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateCommentDto {

    private String content;

    @Builder
    public MateCommentDto(String content) {
        this.content = content;
    }

    public TbMateComment toEntity(TbMate mate, TbUser user) {
        return TbMateComment.builder()
                .content(this.getContent())
                .mate(mate)
                .user(user)
                .build();
    }
}
