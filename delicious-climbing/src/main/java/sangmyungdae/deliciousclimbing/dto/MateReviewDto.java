package sangmyungdae.deliciousclimbing.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateReviewDto {
    private String content;
    private Long postId;
    private Long userId;

    @Builder
    public MateReviewDto(String content,Long postId,Long userId){
        this.content = content;
        this.postId = postId;
        this.userId = userId;
    }

    public TbMateReview toEntity(TbMate mate, TbUser user){
        return TbMateReview.builder()
                .content(this.getContent())
                .user(user)
                .mate(mate)
                .build();
    }
}