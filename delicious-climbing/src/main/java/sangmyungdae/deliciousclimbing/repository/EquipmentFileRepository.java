package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface EquipmentFileRepository extends JpaRepository<TbEquipmentFile, Long> {

    Optional<TbEquipmentFile> findFirstByPostId(Long postId);

    Optional<TbEquipmentFile> findFirstByTbPost(TbPost post);

    List<TbEquipmentFile> findByPostId(Long postId);

    List<TbEquipmentFile> findByTbPost(TbPost post);
}