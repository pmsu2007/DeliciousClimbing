package sangmyungdae.deliciousclimbing.repository;

public interface FamousMountainAdrressRespository extends JpaRepository<TbFamousMountainAddress, Long> {

    List<TbAddress> findByMountainId(Long mountainId);

    List<TbAddress> findByFamousMountain(TbFamousMountain mountain);
    // FamousMountain findByAdrressId(Long addressId);
    // FamousMountain findByAddress(Address address);
}
