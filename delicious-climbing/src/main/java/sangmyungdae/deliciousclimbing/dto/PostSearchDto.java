package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

@Getter
@Setter
public class PostSearchDto {
    private String title;
    private BoardType type;

    @Builder
    public PostSearchDto(String title, BoardType type) {
        this.title = title;
        this.type = type;
    }

}
