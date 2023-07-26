package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter
public class EquipmentReview {

    private Long id;
    private String content;
    private Long postId;
    private Long userId;


    @Builder
    public EquipmentReview(TbEquipmentReview entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.postId = entity.getEquipment().getId();
        this.userId = entity.getUser().getId();
    }
}
