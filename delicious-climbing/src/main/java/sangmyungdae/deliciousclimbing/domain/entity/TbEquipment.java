package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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
}
