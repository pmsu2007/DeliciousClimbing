package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 등산 메이트 생성, 수정 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MateDto {
    private Long id;
    private String title;
    private String content;
    private Integer recruitCount;
    private Boolean recruitStatus;
    private LocalDateTime recruitDate;

    private Long userId;
    private Long mountainId;

    @Builder
    public MateDto(Long id, String title, String content, Integer recruitCount, Boolean recruitStatus, LocalDateTime recruitDate, Long userId, Long mountainId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.userId = userId;
        this.mountainId = mountainId;
    }
}
