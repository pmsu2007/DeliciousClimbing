package sangmyungdae.deliciousclimbing.repository;

public interface MountainAddressRepository extends JpaRepository<TbMountainAddress, Long> {

    List<TbAddress> findByMountainId(Long mountainId);

    List<TbAddress> findByTbMountain(TbMountain mountain);

    TbMountain findByAdrressId(Long addressId);

    TbMountain findByTbAddress(TbAddress address);
}