package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString //출력할때이쁘게해줌
public class Equipment {

    private long id;
    private  String title;
    private String content;
    private int hits;
    private int tradeCost;
    private EquipmentType type;
    private Boolean tradeStatus;
    private String author;
    private Long addressCode;
    private List<EquipmentFile> equipmentFiles = new ArrayList<>();
    private LocalDateTime createdAt;

    public Equipment(int tradeCost) {
        this.tradeCost = tradeCost;
    }

    @Builder
    public Equipment (TbEquipment entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.tradeCost = entity.getTradeCost();
        this.type = entity.getType();
        this.tradeStatus = entity.isTradeStatus();
        this.author = entity.getUser().getNickname();
        this.addressCode = entity.getAddress().getCode();
        this.equipmentFiles = entity.getEquipmentFiles().stream().map(EquipmentFile::new).collect(Collectors.toList());
        this.createdAt = entity.getCreatedAt();
    }

    public static Page<Equipment> toDtoList(Page<TbEquipment>entities){
        Page<Equipment> equipments = entities.map(entity ->
                Equipment.builder().entity(entity).build());

        return equipments;
    }
}