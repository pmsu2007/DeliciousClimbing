package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountainAddress;

import java.util.List;

public interface MountainAddressRepository extends JpaRepository<TbMountainAddress, Long> {

    List<TbAddress> findByMountainId(Long mountainId);

    List<TbAddress> findByTbMountain(TbMountain mountain);

    TbMountain findByAdrressId(Long addressId);

    TbMountain findByTbAddress(TbAddress address);
}