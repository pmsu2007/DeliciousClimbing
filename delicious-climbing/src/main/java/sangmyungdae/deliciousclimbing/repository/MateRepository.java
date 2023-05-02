package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;

public interface MateRepository extends JpaRepository<TbMate, Long> {

    // Page<Mate> findPageAll(Pageable pageable);
    Page<TbMate> findPageByMountainId(Long mountainId, Pageable pageable);

    Page<TbMate> findPageByTbMountain(TbMountain mountain, Pageable pageable);

    Page<TbMate> findPageByMountainIdAndRecruit(Long mountainId, Boolean recruit, Pageable pageable);

    Page<TbMate> findPageByTbMountainAndRecruit(TbMountain mountain, Boolean recruit, Pageable pageable);
}