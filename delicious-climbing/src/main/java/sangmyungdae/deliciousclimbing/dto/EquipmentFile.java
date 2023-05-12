package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;

@Getter
@ToString
public class EquipmentFile {

    private long id;
    private String fileName;

    @Builder
    public EquipmentFile(TbEquipmentFile entity){
        this.id = entity.getId();
        this.fileName = entity.getFileName();
    }
}