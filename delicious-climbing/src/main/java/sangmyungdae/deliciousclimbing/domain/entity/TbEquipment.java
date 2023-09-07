package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "views")
    private Long views;

    @Column(columnDefinition = "integer default 0")
    private int hits;

    @Column(name = "trade_cost")
    private int tradeCost;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Column(name = "trade_status")
    private boolean tradeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private TbUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="address_code")
    private TbAddress address;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TbEquipmentFile> files = new ArrayList<>();

    @OneToMany(mappedBy = "equipment")
    private List<TbEquipmentReview> equipmentReviews = new ArrayList<>();

    @Builder
    public TbEquipment(String title, String content, int hits, int tradeCost, EquipmentType type,
                       boolean tradeStatus, TbUser user, TbAddress address,Long views) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.tradeCost = tradeCost;
        this.type = type;
        this.tradeStatus = tradeStatus;
        this.user = user;
        this.address = address;
        this.views = views;

    }
    public void addFile(TbEquipmentFile file){
        this.files.add(file);
        if(file.getEquipment()!=this){
            file.addPost(this);
        }
    }

    public void update(String title, String content, int tradeCost, boolean tradeStatus, TbAddress address) {
        this.title = title;
        this.content = content;
        this.tradeCost = tradeCost;
        this.tradeStatus = tradeStatus;
        this.address = address;
    }

    public void updateViews(Long views){
        this.views = views;
    }

    public  void updateTradeStatus(boolean tradeStatus){
        this.tradeStatus = tradeStatus;
    }
}
