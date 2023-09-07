package sangmyungdae.deliciousclimbing.dto.equipment;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

@Getter
public class EquipmentDto {
    private EquipmentType type;
    private String title;
    private String content;
    private Long userId;
    private Integer tradeCost;
    private Boolean tradeStatus ;
    private Long addressCode;
    private Long views;

    @Builder
    public EquipmentDto(EquipmentType type, String title, String content,
                         Long userId, Integer tradeCost,Long addressCode,Long views) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.tradeCost = tradeCost;
        this.tradeStatus = Boolean.TRUE;
        //address는?
        this.addressCode = addressCode;
        this.views = views;
    }

    public TbEquipment toEntity(TbUser user, TbAddress address){
        return TbEquipment.builder()
                .type(this.type)
                .title(this.title)
                .content(this.content)
                .tradeCost(this.tradeCost)
                .tradeStatus(true)
                .address(address)
                .user(user)
                .views(0L)
                .build();
    }
}