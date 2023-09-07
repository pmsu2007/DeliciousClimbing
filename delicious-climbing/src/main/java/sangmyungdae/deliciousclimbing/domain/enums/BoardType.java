package sangmyungdae.deliciousclimbing.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    FREE("자유 게시판"),
    AGE("연령별 게시판"),
    REGION("지역별 게시판"),
    REVIEW("등산후기 게시판");

    private String name;
}
