package sangmyungdae.deliciousclimbing.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.service.EquipmentService;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentSearchDto {

    private  Long adressCode;
    private EquipmentType equipmentType;


    @Builder
    public EquipmentSearchDto(Long adressCode,EquipmentType equipmentType,boolean tradeStatusFiltering){
        this.adressCode = adressCode;
        this.equipmentType = equipmentType;
    }

}
