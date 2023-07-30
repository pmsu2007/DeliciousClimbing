package sangmyungdae.deliciousclimbing.dto.review;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class Review {
    private Long id;
    private ReviewType type;
    private String content;
    private User reviewer;
    private String createdAt;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class User {
        private Long id;
        private String nickname;
        private String imageUrl;

        public User(TbUser user) {
            this.id = user.getId();
            this.nickname = user.getNickname();
            this.imageUrl = user.getStoreFileName();
        }
    }

    @Builder
    public Review(TbReview entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.content = entity.getContent();
        this.reviewer = new User(entity.getReviewer());
        this.createdAt = entity.getCreatedAt();
    }

    public static List<Review> toDtoList(List<TbReview> entities) {
        return entities.stream().map(entity ->
                Review.builder().entity(entity).build()).collect(Collectors.toList());
    }
}
