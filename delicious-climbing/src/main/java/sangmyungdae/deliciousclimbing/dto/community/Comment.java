package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbComment;

@Getter
@ToString
public class Comment {
    private long id;
    private String content;
    private String author;
    private String authorImgUrl;


    @Builder
    public Comment(TbComment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getUser().getNickname();
        this.authorImgUrl = entity.getUser().getImageUrl();
    }
}
