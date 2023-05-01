package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainAddress;

import java.util.List;

public interface FamousMountainAdrressRepository extends JpaRepository<TbFamousMountainAddress, Long> {

    List<TbAddress> findByMountainId(Long mountainId);

    List<TbAddress> findByFamousMountain(TbFamousMountain mountain);
    // FamousMountain findByAdrressId(Long addressId);
    // FamousMountain findByAddress(Address address);
}
