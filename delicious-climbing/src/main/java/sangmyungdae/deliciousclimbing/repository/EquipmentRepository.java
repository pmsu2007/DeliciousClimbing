package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

public interface EquipmentRepository extends JpaRepository<TbEquipment, Long> {

    Page<TbEquipment> findPageByAddress_Code(Long code, Pageable pageable);

    Page<TbEquipment> findPageByEquipment_Type(EquipmentType type, Pageable pageable);

    Page<TbEquipment> findPageByAddress_CodeAndEquipment_Type(Long code, EquipmentType type, Pageable pageable);

    Page<TbEquipment> findPageByTradeStatus(Boolean tradeStatus, Pageable pageable);

}