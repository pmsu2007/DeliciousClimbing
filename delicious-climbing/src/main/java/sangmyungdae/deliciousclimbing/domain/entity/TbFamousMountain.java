package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN")
public class TbFamousMountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private String info;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer period;

    @Column(columnDefinition = "integer default 0")
    private Integer likes;

    @Column(name = "image_url")
    private String imageUrl;

    private String reason;

    private String season;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainAddress> famousMountainAddresses = new ArrayList<>();

    @Builder
    public TbFamousMountain(String name, Integer height, String info, Difficulty difficulty, Integer period,
                            Integer likes, String imageUrl, String reason, String season, Region region) {
        this.name = name;
        this.height = height;
        this.info = info;
        this.difficulty = difficulty;
        this.period = period;
        this.likes = likes;
        this.imageUrl = imageUrl;
        this.reason = reason;
        this.season = season;
        this.region = region;
    }
}
