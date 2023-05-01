package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

public interface EquipmentRepository extends JpaRepository<TbEquipment, Long> {

    Page<TbEquipment> findPageByAddressId(Long id, Pageable pageable);

    Page<TbEquipment> findPageByAddressIdAndType(Long id, EquipmentType type, Pageable pageable);

    Page<TbEquipment> findPageByAddressIdAndTypeAndTrade(Long id, EquipmentType type, Boolean trade, Pageable pageable);

    Page<TbEquipment> findPageByTrade(Boolean trade, Pageable pageable);

}