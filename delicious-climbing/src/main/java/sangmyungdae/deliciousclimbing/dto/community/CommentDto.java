package sangmyungdae.deliciousclimbing.dto.community;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sangmyungdae.deliciousclimbing.domain.entity.TbComment;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

@Getter
@Setter
public class CommentDto {
    private String content;

    @Builder
    public CommentDto(String content) {
        this.content = content;
    }

    public TbComment toEntity(TbPost post, TbUser user) {
        return TbComment.builder()
                .content(this.content)
                .post(post)
                .user(user)
                .build();
    }
}
