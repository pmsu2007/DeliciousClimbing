package sangmyungdae.deliciousclimbing.dto;

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
    private Boolean tradeStatus;
    private Long addressCode;

    @Builder
    public EquipmentDto(EquipmentType type, String title, String content,
                        Long userId, Integer tradeCost, Boolean tradeStatus,Long addressCode) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.tradeCost = tradeCost;
        this.tradeStatus = tradeStatus;
        //address는?
        this.addressCode = addressCode;
    }

    public TbEquipment toEntity(TbUser user, TbAddress address){
        return TbEquipment.builder()
                .type(this.type)
                .title(this.title)
                .content(this.content)
                .tradeCost(this.tradeCost)
                .tradeStatus(this.tradeStatus)
                .address(address)
                .user(user)
                .build();
    }
}