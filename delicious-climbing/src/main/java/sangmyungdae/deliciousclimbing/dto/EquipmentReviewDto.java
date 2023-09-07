package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter
public class EquipmentReviewDto {
    private String content;
    private Long postId;
    private Long userId;

    @Builder
    public EquipmentReviewDto(String content,Long postId,Long userId){
        this.content = content;
        this.postId = postId;
        this.userId = userId;
    }

    public TbEquipmentReview toEntity(TbEquipment equipment, TbUser user){
        return TbEquipmentReview.builder()
                .content(this.getContent())
                .user(user)
                .equipment(equipment)
                .build();
    }
}
