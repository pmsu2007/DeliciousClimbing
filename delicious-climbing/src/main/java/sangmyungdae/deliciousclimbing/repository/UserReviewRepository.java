package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.entity.TbUserReview;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<TbUserReview, Long> {
    List<TbUserReview> findByUser_Id(Long userId);
    List<TbUserReview> findByUser(TbUser user);

}
