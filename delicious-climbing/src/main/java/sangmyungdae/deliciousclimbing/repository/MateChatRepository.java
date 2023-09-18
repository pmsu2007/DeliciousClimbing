package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChat;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateChatRoom;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;

public interface MateChatRepository extends JpaRepository<TbMateChat, Long> {
    List<TbMateChat> findByParticipant(TbUser participant);
    boolean existsByParticipantAndRoom(TbUser participant, TbMateChatRoom room);
    TbMateChat findByParticipantAndRoom(TbUser participant, TbMateChatRoom room);
}
