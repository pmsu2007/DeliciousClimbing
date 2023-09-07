package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatMessage;

public interface ChatMessageRepository extends JpaRepository<TbChatMessage, Long> {
}
