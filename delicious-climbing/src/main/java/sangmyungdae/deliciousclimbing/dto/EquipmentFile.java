package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;

@Getter
@ToString
public class EquipmentFile {

    private long id;
    private String storeFileName;
    private String uploadFileName;

    @Builder
    public EquipmentFile(TbEquipmentFile entity){
        this.id = entity.getId();
        this.storeFileName = entity.getStoreFileName();
        this.uploadFileName = entity.getUploadFileName();
    }
}