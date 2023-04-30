package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN")
public class TbFamousMountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // not null
    private String name;

    // not null
    private Integer height;

    // not null
    private String info;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer period;

    private Integer likes;

    @Column(name = "image_url")
    private String imageUrl;

    private String reason;

    private String season;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainAddress> famousMountainAdresses = new ArrayList<>();
}
