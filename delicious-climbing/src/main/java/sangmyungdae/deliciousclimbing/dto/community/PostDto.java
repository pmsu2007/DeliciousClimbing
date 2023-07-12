package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

import java.util.List;


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
