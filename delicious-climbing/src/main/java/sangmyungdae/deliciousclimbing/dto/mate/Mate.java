package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;

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
    private String updatedAt;

    private Long userId;
    private String nickName;
    private String userImageUrl;

    private Long mountainId;
    private String mountainName;

    @Builder
    public Mate(TbMate entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.recruitCount = entity.getRecruitCount();
        this.recruitStatus = entity.isRecruitStatus();
        this.recruitDate = entity.getRecruitDate();
        this.updatedAt = entity.getUpdatedAt();
        this.userId = entity.getUser().getId();
        this.nickName = entity.getUser().getNickname();
        this.userImageUrl = entity.getUser().getImageUrl();
        this.mountainId = entity.getMountain().getId();
        this.mountainName = entity.getMountain().getName();
    }
}
