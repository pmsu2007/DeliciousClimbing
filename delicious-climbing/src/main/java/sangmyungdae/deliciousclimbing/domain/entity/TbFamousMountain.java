package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN")
public class TbFamousMountain {
    @Id
    private Long id; // 산 id

    @Column(nullable = false)
    private String name;

    private int height;

    @Column(length = 3000)
    private String info;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String period;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(length = 1000)
    private String reason;

    private String season;


    @Column(columnDefinition = "integer default 0")
    private int likes;

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainAddress> famousMountainAddresses = new ArrayList<>();
  
    @Builder
    public TbFamousMountain(Long id, String name, int height, String info, Difficulty difficulty, String period,
                            String imageUrl, String reason, String season) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.info = info;
        this.difficulty = difficulty;
        this.period = period;
        this.imageUrl = imageUrl;
        this.reason = reason;
        this.season = season;
    }
    public void updateLike(int likes) {
        this.likes = likes;
    }
}
