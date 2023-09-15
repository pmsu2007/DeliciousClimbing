package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<TbAddress, Long> {
    Optional<TbAddress> findByCode(Long code);
    Optional<TbAddress> findBySidoAndSigungu(String sido, String sigungu);
}
