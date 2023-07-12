package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

@Getter
@Setter
public class PostSearchDto {
    private String title;
    private String sort;
    private BoardType type;

    @Builder
    public PostSearchDto(String title, String sort, BoardType type) {
        this.title = title;
        this.sort = sort;
        this.type = type;
    }

}
