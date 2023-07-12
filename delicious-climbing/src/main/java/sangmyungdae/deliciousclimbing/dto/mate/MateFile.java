package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateFile;

import java.time.LocalDateTime;

/**
 * 등산 메이트 파일 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateFile {

    private Long id;
    private String fileName;
    private String updatedAt;

    @Builder
    public MateFile(TbMateFile entity) {
        this.id = entity.getId();
        this.fileName = entity.getFileName();
        this.updatedAt = entity.getUpdatedAt();
    }
}
