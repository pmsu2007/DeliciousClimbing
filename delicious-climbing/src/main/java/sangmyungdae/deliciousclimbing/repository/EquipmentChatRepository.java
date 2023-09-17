package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChat;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;

public interface EquipmentChatRepository extends JpaRepository<TbEquipmentChat, Long> {
    List<TbEquipmentChat> findByCreator(TbUser creator);
    boolean existsByCreatorAndRoom(TbUser creator, TbEquipmentChatRoom room);
    TbEquipmentChat findByCreatorAndRoom(TbUser creator, TbEquipmentChatRoom room);
}
