package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbReview;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;
import sangmyungdae.deliciousclimbing.dto.review.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<TbReview, Long> {
    List<TbReview> findByType(ReviewType type);

}
