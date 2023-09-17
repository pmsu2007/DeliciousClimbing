package sangmyungdae.deliciousclimbing.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatRoomType {

    MATE("등산 메이트"),
    EQUIPMENT("등산 장비 거래");

    private String name;
}
