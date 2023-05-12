package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

@Getter
@Setter
public class FileDto {
    private String fileName;
    private Long postId;

    @Builder
    public FileDto(String fileName, Long postId) {
        this.fileName = fileName;
        this.postId = postId;
    }

    public TbFile toEntity(TbPost post) {
        return TbFile.builder()
                .fileName(this.fileName)
                .post(post)
                .build();
    }

}
