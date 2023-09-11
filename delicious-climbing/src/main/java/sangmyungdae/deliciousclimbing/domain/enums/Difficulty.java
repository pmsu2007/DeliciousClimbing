package sangmyungdae.deliciousclimbing.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    UPPER("상"),
    MIDDLE("중"),
    LOWER("하");

    private String name;
}
