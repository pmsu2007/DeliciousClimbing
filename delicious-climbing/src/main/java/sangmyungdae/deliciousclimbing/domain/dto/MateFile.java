package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 등산 메이트 파일 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateFile {

    private Long id;
    private String fileName;
    private LocalDateTime updatedAt;

    @Builder
    public MateFile(Long id, String fileName, LocalDateTime updatedAt) {
        this.id = id;
        this.fileName = fileName;
        this.updatedAt = updatedAt;
    }
}
