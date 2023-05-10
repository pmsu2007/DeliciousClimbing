package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_FAMOUSMOUNTAIN_LIKE")
public class TbFamousMountainLike extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TbUser user;

    @ManyToOne
    @JoinColumn(name = "famousMountain_id")
    private TbFamousMountain famousMountain;

    @Builder
    public TbFamousMountainLike(TbUser user, TbFamousMountain famousMountain) {
        this.user = user;
        this.famousMountain = famousMountain;
    }
}
