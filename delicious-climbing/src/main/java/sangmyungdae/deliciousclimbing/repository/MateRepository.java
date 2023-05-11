package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;

public interface MateRepository extends JpaRepository<TbMate, Long> {

    Page<TbMate> findPageByRecruitStatus(Boolean recruitStatus, Pageable pageable);
    Page<TbMate> findPageByMountain_Id(Long mountainId, Pageable pageable);
    Page<TbMate> findPageByMountain(TbMountain mountain, Pageable pageable);
    Page<TbMate> findPageByMountain_IdAndRecruitStatus(Long mountainId, Boolean recruitStatus, Pageable pageable);
    Page<TbMate> findPageByMountainAndRecruitStatus(TbMountain mountain, Boolean recruitStatus, Pageable pageable);
}