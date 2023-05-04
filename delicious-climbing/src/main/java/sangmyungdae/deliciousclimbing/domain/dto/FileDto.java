package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 등산 메이트 파일 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileDto {

    private Long id;
    private String fileName;

    @Builder
    public FileDto(Long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
}
