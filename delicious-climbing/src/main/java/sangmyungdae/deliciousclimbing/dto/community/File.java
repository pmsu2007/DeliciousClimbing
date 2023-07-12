package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;

@Getter
@ToString
public class File {

    private long id;
    private String uploadFileName;
    private String storeFileName;
    @Builder
    public File(TbFile entity) {
        this.id = entity.getId();
        this.uploadFileName = entity.getUploadFileName();
        this.storeFileName = entity.getStoreFileName();
    }
}
