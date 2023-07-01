package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainAddress;

import java.util.List;

public interface FamousMountainAddressRepository extends JpaRepository<TbFamousMountainAddress, Long> {

    // famousMountain_id로 주소 조회
    List<TbFamousMountainAddress> findByFamousMountain_Id(Long famousMountainId);
    List<TbFamousMountainAddress> findByFamousMountain(TbFamousMountain famousMountain);

    // 주소로 산 조회
    List<TbFamousMountainAddress> findByAddress_Code(Long addressCode);
    List<TbFamousMountainAddress> findByAddress(TbAddress address);
}
