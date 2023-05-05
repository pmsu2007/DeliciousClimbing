package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountainAddress;

import java.util.List;

public interface MountainAddressRepository extends JpaRepository<TbMountainAddress, Long> {

    List<TbMountainAddress> findByMountain_Id(Long mountainId);

    List<TbMountainAddress> findByMountain(TbMountain mountain);

    TbMountainAddress findByAddress_Code(Long addressCode);

    TbMountainAddress findByAddress(TbAddress address);
}