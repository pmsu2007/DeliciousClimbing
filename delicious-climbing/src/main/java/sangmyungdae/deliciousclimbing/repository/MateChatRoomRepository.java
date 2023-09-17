package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatRoom;


public interface MateChatRoomRepository extends JpaRepository<TbMateChatRoom, Long> {
}
