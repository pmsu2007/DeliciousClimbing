package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainRegion;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import java.util.List;

public interface FamousMountainRegionRepository extends JpaRepository<TbFamousMountainRegion, Long> {

    List<TbFamousMountainRegion> findByRegion(Region region);

}
