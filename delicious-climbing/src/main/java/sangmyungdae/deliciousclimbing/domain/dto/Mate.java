package sangmyungdae.deliciousclimbing.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 등산 메이트 생성, 수정 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mate {

    private Long id;
    private String title;
    private String content;
    private Integer hits;
    private Integer recruitCount;
    private Boolean recruitStatus;
    private LocalDateTime recruitDate;
    private LocalDateTime updatedAt;

    private Long userId;
    private String nickname;
    private String imageUrl;

    private Long mountainId;
    private String mountainName;

    @Builder
    public Mate(Long id, String title, String content, Integer hits, Integer recruitCount, Boolean recruitStatus, LocalDateTime recruitDate, LocalDateTime updatedAt,
                Long userId, String nickname, String imageUrl,
                Long mountainId, String mountainName) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.updatedAt = updatedAt;

        this.userId = userId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;

        this.mountainId = mountainId;
        this.mountainName = mountainName;
    }
}
