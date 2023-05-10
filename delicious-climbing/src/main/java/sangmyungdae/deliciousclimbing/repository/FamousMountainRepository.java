package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

public interface FamousMountainRepository extends JpaRepository<TbFamousMountain, Long> {
    Page<TbFamousMountain> findPageByNameContaining(String searchKeyword, Pageable pageable);
    Page<TbFamousMountain> findPageByRegion(Region region, Pageable pageable);
    Page<TbFamousMountain> findPageByRegionAndNameContaining(Region region, String searchKeyword, Pageable pageable);
}