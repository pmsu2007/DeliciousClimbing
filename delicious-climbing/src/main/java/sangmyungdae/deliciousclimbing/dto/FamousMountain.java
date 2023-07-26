package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

public class FamousMountain {
    private long id;
    private String name;
    private int height;
    private String info;
    private Difficulty difficulty;
    private int period;
    private int likes;
    private String imageUrl;
    private String reason;
    private String season;
    private Region region;

    @Builder
    public FamousMountain(TbFamousMountain entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.height = entity.getHeight();
        this.info = entity.getInfo();
        this.difficulty = entity.getDifficulty();
        this.period = entity.getPeriod();
        this.imageUrl = entity.getImageUrl();
        this.reason = entity.getReason();
        this.season = entity.getSeason();
    }
}
