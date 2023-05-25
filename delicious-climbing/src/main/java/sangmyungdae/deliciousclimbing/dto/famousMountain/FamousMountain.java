package sangmyungdae.deliciousclimbing.dto.famousMountain;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamousMountain {
    private long id;
    private String name;
    private int height;
    private String info;
    private Difficulty difficulty;
    private int period;
    private int likes;
    private Boolean likeCheck; // 0: unlike, 1: like
    private String imageUrl;
    private String reason;
    private String season;
    private String detailRegion;

    @Builder
    public FamousMountain(TbFamousMountain entity, String detailRegion, Boolean likeCheck) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.height = entity.getHeight();
        this.info = entity.getInfo();
        this.difficulty = entity.getDifficulty();
        this.period = entity.getPeriod();
        this.likes = entity.getFamousMountainLikes().size();
        this.likeCheck = likeCheck;
        this.imageUrl = entity.getImageUrl();
        this.reason = entity.getReason();
        this.season = entity.getSeason();
        this.detailRegion = detailRegion;
    }
}
