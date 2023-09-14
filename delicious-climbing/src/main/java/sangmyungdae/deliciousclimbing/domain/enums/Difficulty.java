package sangmyungdae.deliciousclimbing.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Difficulty {
    UPPER("상"),
    MIDDLE("중"),
    LOWER("하");

    private String name;

    public static Difficulty valueOfName(String label) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(label))
                .findAny()
                .orElse(null);

    }
}
