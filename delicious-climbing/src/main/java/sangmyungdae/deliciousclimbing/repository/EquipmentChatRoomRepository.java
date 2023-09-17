package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.Optional;

public interface EquipmentChatRoomRepository extends JpaRepository<TbEquipmentChatRoom, Long> {
    boolean existsByCreatorAndEquipment(TbUser user, TbEquipment equipment);
    Optional<TbEquipmentChatRoom> findByCreatorAndEquipment(TbUser user, TbEquipment equipment);
}
