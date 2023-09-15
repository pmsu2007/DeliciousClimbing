package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import java.util.List;

public interface FamousMountainRepository extends JpaRepository<TbFamousMountain, Long> {
    List<TbFamousMountain> findByNameContaining(String searchKeyword, Pageable pageable);
//    List<TbFamousMountain> findByRegion(Region region, Pageable pageable);
//    List<TbFamousMountain> findByRegionAndNameContaining(Region region, String searchKeyword, Pageable pageable);

    //list<TbFamousMountain> 으로 조회 할 수 있는 방법은 없나>?
    List<TbFamousMountain> findByNameContainingAndIdIn(String searchKeyword, List<Long> ids, Pageable pageable);
    List<TbFamousMountain> findByIdIn(List<Long> ids, Pageable pageable);

    // 산 랜덤 추출 -> 1개만 뽑 -> 나는 3개
    @Query(value = "SELECT * FROM TB_FAMOUSMOUNTAIN order by RAND() limit 3",nativeQuery = true)
    List<TbFamousMountain> findAllRand();
}