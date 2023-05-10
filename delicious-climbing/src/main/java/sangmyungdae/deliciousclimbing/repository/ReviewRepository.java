package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbReview;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;

public interface ReviewRepository extends JpaRepository<TbReview, Long> {
    TbReview findByIdAndType(Long id, ReviewType type);
}
