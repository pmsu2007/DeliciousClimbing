package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;


public interface ChatRoomRepository extends JpaRepository<TbChatRoom, Long> {
}
