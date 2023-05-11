package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_EQUIPMENT_REVIEW")
public class TbEquipmentReview extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private TbEquipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private TbUser user;

    @Builder
    public TbEquipmentReview(String content, TbEquipment equipment, TbUser user) {
        this.content = content;
        this.equipment = equipment;
        this.user = user;
    }

    public void update(String content) {
        this.content = content;
    }
}
