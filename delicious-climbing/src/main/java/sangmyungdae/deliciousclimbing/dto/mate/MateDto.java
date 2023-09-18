package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import sangmyungdae.deliciousclimbing.domain.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 등산 메이트 생성, 수정 Request DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MateDto {

    private String title;
    private String content;
    @Value("0")
    private Integer recruitCount;
    private Boolean recruitStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitDate;

    private Long mountainId;

    @Builder
    public MateDto(String title, String content, Integer recruitCount, Boolean recruitStatus, LocalDate recruitDate,  Long mountainId) {
        this.title = title;
        this.content = content;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.mountainId = mountainId;
    }

    public TbMate toEntity(TbUser user, TbFamousMountain famousMountain, TbMateChatRoom mateChatRoom) {
        return TbMate.builder()
                .title(this.title)
                .content(this.content)
                .recruitCount(this.recruitCount)
                .recruitStatus(this.getRecruitStatus())
                .recruitDate(this.getRecruitDate())
                .user(user)
                .famousMountain(famousMountain)
                .mateChatRoom(mateChatRoom)
                .build();
    }
}
