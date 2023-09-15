package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN_REGION")
public class TbFamousMountainRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "mountain_id")
    private TbFamousMountain famousMountain;

    @Builder
    public TbFamousMountainRegion(Region region, TbFamousMountain famousMountain) {
        this.region = region;
        this.famousMountain = famousMountain;
    }
}
