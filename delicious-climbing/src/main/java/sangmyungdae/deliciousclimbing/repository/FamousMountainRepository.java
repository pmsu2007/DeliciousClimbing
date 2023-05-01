package sangmyungdae.deliciousclimbing.repository;

public interface FamousMountainRepository extends JpaRepository<TbFamousMountain, Long> {

    //Page<FamousMountain> findAll(Pageable pageable);
    Page<TbFamousMountain> findPageByNameContaining(String searchKeword, Pageable pageable);

    Page<TbFamousMountain> findPageByRegion(Region region, Pageable pageable);

    Page<TbFamousMountain> findPageByRegionAndNameContaining(Region region, String searchKeyword, Pageable pageable);
}