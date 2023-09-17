package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatMessage;

public interface MateChatMessageRepository extends JpaRepository<TbMateChatMessage, Long> {
}
