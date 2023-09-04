package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatParticipant;
import sangmyungdae.deliciousclimbing.domain.entity.TbChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;

public interface ChatParticipantRepository extends JpaRepository<TbChatParticipant, Long> {
    List<TbChatParticipant> findByCreator(TbUser creator);
    boolean existsByCreator(TbUser creator);
    TbChatParticipant findByCreatorAndRoom(TbUser creator, TbChatRoom room);
}
