package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;

@Getter
@ToString
public class File {

    private long id;
    private String fileName;

    @Builder
    public File(TbFile entity) {
        this.id = entity.getId();
        this.fileName = entity.getFileName();
    }
}
