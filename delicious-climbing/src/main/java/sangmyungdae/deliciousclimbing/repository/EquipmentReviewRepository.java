package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentReview;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;

public interface EquipmentReviewRepository extends JpaRepository<TbEquipmentReview, Long> {
    List<TbEquipmentReview> findByUser_Id(Long userId);
    List<TbEquipmentReview> findByUser(TbUser user);
}
