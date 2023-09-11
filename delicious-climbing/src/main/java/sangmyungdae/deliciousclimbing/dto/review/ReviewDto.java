package sangmyungdae.deliciousclimbing.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;

@Getter @Setter
public class ReviewDto {
    private ReviewType type;
    private String content;

    @Builder
    public ReviewDto(ReviewType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TbReview toEntity(TbUser reviewer, TbUser user) {
        return TbReview.builder()
                .type(this.type)
                .content(this.content)
                .reviewer(reviewer)
                .user(user)
                .build();
    }
}
