package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainLike;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;
import java.util.Optional;

public interface FamousMountainLikeRepository extends JpaRepository<TbFamousMountainLike, Long> {
    List<TbFamousMountainLike> findByFamousMountain_Id(Long famousMountainId);
    List<TbFamousMountainLike> findByFamousMountain(TbFamousMountain famousMountain);

    List<TbFamousMountainLike> findByUser_Id(Long userId);
    List<TbFamousMountainLike> findByUser(TbUser user);

    Optional<TbFamousMountainLike> findByUser_IdAndFamousMountain_Id(Long userId, Long famousMountainId);
}
