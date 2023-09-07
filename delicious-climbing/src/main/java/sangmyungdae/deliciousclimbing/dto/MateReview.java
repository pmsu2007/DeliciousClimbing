package sangmyungdae.deliciousclimbing.dto;


import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateReview;

@Getter
public class MateReview {
    private Long id;
    private String content;
    private Long userId;
    private Long postId;
    @Builder
    public MateReview(TbMateReview entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.userId = entity.getUser().getId();
        this.postId = entity.getMate().getId();
    }
}
