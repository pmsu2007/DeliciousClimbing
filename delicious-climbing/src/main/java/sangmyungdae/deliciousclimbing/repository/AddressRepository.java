package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

public interface AddressRepository extends JpaRepository<TbAddress, Long> {
    TbAddress findByCode(Long code);
}
