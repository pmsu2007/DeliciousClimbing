package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.time.LocalDateTime;

/**
 * 등산 메이트 생성, 수정 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateDto {

    private String title;
    private String content;
    private Integer recruitCount;
    private Boolean recruitStatus;
    private LocalDateTime recruitDate;

    private Long mountainId;

    @Builder
    public MateDto(String title, String content, Integer recruitCount, Boolean recruitStatus, LocalDateTime recruitDate,  Long mountainId) {
        this.title = title;
        this.content = content;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.mountainId = mountainId;
    }

    public TbMate toEntity(TbUser user, TbMountain mountain) {
        return TbMate.builder()
                .title(this.title)
                .content(this.content)
                .recruitCount(this.recruitCount)
                .recruitStatus(this.getRecruitStatus())
                .recruitDate(this.getRecruitDate())
                .user(user)
                .mountain(mountain)
                .build();
    }
}
