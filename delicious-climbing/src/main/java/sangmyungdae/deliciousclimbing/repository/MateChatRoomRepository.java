package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.*;

import java.util.Optional;


public interface MateChatRoomRepository extends JpaRepository<TbMateChatRoom, Long> {
    boolean existsByCreatorAndMate(TbUser user, TbMate mate);
    Optional<TbMateChatRoom> findByCreatorAndMate(TbUser user, TbMate mate);

}

