package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;


@Getter
public class PostDto {
    private BoardType type;
    private String title;
    private String content;
    private Long userId;

    @Builder
    public PostDto(BoardType type, String title, String content, Long userId) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public TbPost toEntity(TbUser user) {
        return TbPost.builder()
                .type(this.type)
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
