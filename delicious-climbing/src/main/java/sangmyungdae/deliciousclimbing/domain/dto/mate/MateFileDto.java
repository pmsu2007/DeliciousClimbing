package sangmyungdae.deliciousclimbing.domain.dto.mate;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateFile;

/**
 * 등산 메이트 파일 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateFileDto {

    private String fileName;

    @Builder
    public MateFileDto(String fileName) {
        this.fileName = fileName;
    }


    public TbMateFile toEntity(TbMate mate) {
        return TbMateFile.builder()
                .fileName(this.fileName)
                .mate(mate)
                .build();
    }
}
