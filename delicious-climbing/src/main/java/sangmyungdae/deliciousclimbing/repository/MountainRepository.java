package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMountain;

public interface MountainRepository extends JpaRepository<TbMountain, Long> {

}
