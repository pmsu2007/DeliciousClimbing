package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 등산 메이트 조회 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateSearchDto {

    private Long mountainId;
    private boolean recruitStatusFiltering; //0: 모든 글 보기, 1: 모집중인 글만 보기

    @Builder
    public MateSearchDto(Long mountainId, boolean recruitStatusFiltering) {
        this.mountainId = mountainId;
        this.recruitStatusFiltering = recruitStatusFiltering;
    }
}
