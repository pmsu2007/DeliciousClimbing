package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_EQUIPMENT")
public class TbEquipment extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0")
    private Integer hits;

    @Column(name = "trade_cost", nullable = false)
    private Integer tradeCost;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Column(name = "trade_status", nullable = false)
    private Boolean tradeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private TbUser user;

    @OneToOne(fetch = FetchType.LAZY)
    private TbAddress address;

    @OneToMany(mappedBy = "equipment")
    private List<TbEquipmentFile> equipmentFiles = new ArrayList<>();

    @Builder
    public TbEquipment(String title, String content, Integer hits, Integer tradeCost, EquipmentType type,
                       Boolean tradeStatus, TbUser user, TbAddress address) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.tradeCost = tradeCost;
        this.type = type;
        this.tradeStatus = tradeStatus;
        this.user = user;
        this.address = address;
    }
}
