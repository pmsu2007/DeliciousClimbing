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

    private int height;

    @Column(nullable = false)
    private String info;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private int period;

    @Column(name = "image_url")
    private String imageUrl;

    private String reason;

    private String season;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainAddress> famousMountainAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainLike> famousMountainLikes = new ArrayList<>();
    @Builder
    public TbFamousMountain(String name, int height, String info, Difficulty difficulty, int period,
                            String imageUrl, String reason, String season, Region region) {
        this.name = name;
        this.height = height;
        this.info = info;
        this.difficulty = difficulty;
        this.period = period;
        this.imageUrl = imageUrl;
        this.reason = reason;
        this.season = season;
        this.region = region;
    }
}
