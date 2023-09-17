package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChatMessage;

public interface EquipmentChatMessageRepository extends JpaRepository<TbEquipmentChatMessage, Long> {
}
