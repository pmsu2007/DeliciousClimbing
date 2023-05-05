package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipmentFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface EquipmentFileRepository extends JpaRepository<TbEquipmentFile, Long> {

    Optional<TbEquipmentFile> findFirstByEquipment_Id(Long equipmentId);

    Optional<TbEquipmentFile> findFirstByEquipment(TbEquipment equipment);

    List<TbEquipmentFile> findByEquipment_Id(Long equipmentId);

    List<TbEquipmentFile> findByEquipment(TbEquipment equipment);
}