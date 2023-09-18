package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 등산 메이트 생성, 수정 Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Mate {

    private Long id;
    private String title;
    private String content;
    private Integer hits;
    private Integer recruitCount;
    private Boolean recruitStatus;
    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 (E)")
    private LocalDate recruitDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
    private LocalDateTime updatedAt;

    private Long userId;
    private String nickName;
    private String userImageUrl;
    private String userEmail;

    private Long roomId;
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
        this.updatedAt = LocalDateTime.parse(entity.getUpdatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.userId = entity.getUser().getId();
        this.nickName = entity.getUser().getNickname();
        this.userImageUrl = entity.getUser().getStoreFileName();
        this.userEmail = entity.getUser().getEmail();
        this.mountainId = entity.getFamousMountain().getId();
        this.mountainName = entity.getFamousMountain().getName();
        this.roomId = entity.getMateChatRoom().getId();
    }
}
