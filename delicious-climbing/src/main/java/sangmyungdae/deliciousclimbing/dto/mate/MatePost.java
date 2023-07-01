package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import org.springframework.data.domain.Page;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 등산 메이트  게시글(+ 댓글 + 파일) Response DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatePost {

    private Mate mate;
    private List<MateComment> comments;
    private List<MateFile> files;

    @Builder
    public MatePost(TbMate entity) {
        this.mate = Mate.builder().entity(entity).build();
        this.comments = entity.getMateComments().stream().map(MateComment::new).collect(Collectors.toList());
        this.files = entity.getMateFiles().stream().map(MateFile::new).collect(Collectors.toList());
    }

    public static Page<MatePost> toDtoList(Page<TbMate> entities) {
        Page<MatePost> posts = entities.map(entity ->
                MatePost.builder().entity(entity).build());

        return posts;
    }
}
