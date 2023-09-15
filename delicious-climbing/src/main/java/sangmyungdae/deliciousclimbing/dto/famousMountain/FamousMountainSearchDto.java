package sangmyungdae.deliciousclimbing.dto.famousMountain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamousMountainSearchDto {
    Region region;
    String keyword;

    @Builder
    public FamousMountainSearchDto(Region region, String keyword) {
        this.region = region;
        this.keyword = keyword;
        nullProcessing();
    }

    private void nullProcessing() {
        if (this.keyword == null)
            this.keyword = "";
    }
}
