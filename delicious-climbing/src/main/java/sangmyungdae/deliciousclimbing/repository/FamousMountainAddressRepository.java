package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainAddress;

import java.util.List;

public interface FamousMountainAddressRepository extends JpaRepository<TbFamousMountainAddress, Long> {

    List<TbFamousMountainAddress> findByFamousMountain_Id(Long famousMountainId);

    List<TbFamousMountainAddress> findByFamousMountain(TbFamousMountain famousMountain);

    TbFamousMountain findByAddress_Code(Long addressCode);

    TbFamousMountain findByAddress(TbAddress address);
}
