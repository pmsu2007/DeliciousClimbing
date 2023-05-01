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

    private String title;

    private String content;

    private Integer hits;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    private Boolean trade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private TbUser user;

    @OneToOne(fetch = FetchType.LAZY)
    private TbAddress address;

    @OneToMany(mappedBy = "equipment")
    private List<TbEquipmentFile> equipmentFiles = new ArrayList<>();

    @Builder
    public TbEquipment(String title, String content, Integer hits, Integer cost, EquipmentType type, Boolean trade,
                       TbUser user, TbAddress address) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.cost = cost;
        this.type = type;
        this.trade = trade;
        this.user = user;
        this.address = address;
    }
}
