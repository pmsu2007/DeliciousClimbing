package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;

public interface MateRepository extends JpaRepository<TbMate, Long> {

    Page<TbMate> findPageByRecruitStatus(Boolean recruitStatus, Pageable pageable);
    Page<TbMate> findPageByFamousMountain_Id(Long famousMountainId, Pageable pageable);
    Page<TbMate> findPageByFamousMountain(TbFamousMountain famousMountain, Pageable pageable);
    Page<TbMate> findPageByFamousMountain_IdAndRecruitStatus(Long famousMountainId, Boolean recruitStatus, Pageable pageable);
    Page<TbMate> findPageByFamousMountainAndRecruitStatus(TbFamousMountain famousMountain, Boolean recruitStatus, Pageable pageable);
}