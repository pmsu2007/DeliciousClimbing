package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
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
}
