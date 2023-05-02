package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TbUser, Long> {

    Optional<TbUser> findByEmail(String email);
}
