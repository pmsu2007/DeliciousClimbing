package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;

@Getter
@Setter
public class EquipmentFileDto {
    private String fileName;
    private Long postId;

    @Builder
    public EquipmentFileDto(String fileName, Long postId){
        this.fileName = fileName;
        this.postId = postId;
    }

    public TbEquipmentFile toEntity(TbEquipment equipment){
        return TbEquipmentFile.builder()
                .fileName(this.fileName)
                .equipment(equipment)
                .build();
    }
}
