package sangmyungdae.deliciousclimbing.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

@Getter
@Setter
public class FileDto {
    private String uploadFileName;
    private String storeFileName;

    @Builder
    public FileDto(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}