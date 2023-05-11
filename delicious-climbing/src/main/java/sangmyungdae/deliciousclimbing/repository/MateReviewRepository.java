package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;

public interface MateReviewRepository extends JpaRepository<TbMateReview, Long> {
    List<TbMateReview> findByUser_Id(Long userId);
    List<TbMateReview> findByUser(TbUser user);
}
