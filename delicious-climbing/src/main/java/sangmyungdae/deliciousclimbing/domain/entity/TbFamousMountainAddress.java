package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN_ADDRESS")
public class TbFamousMountainAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mountain_id")
    private TbFamousMountain famousMountain;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private TbAddress address;

    @Builder
    public TbFamousMountainAddress(TbFamousMountain famousMountain, TbAddress address) {
        this.famousMountain = famousMountain;
        this.address = address;
    }
}
