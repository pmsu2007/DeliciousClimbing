package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<TbEquipment, Long> {


    List<TbEquipment> findPageByAddress_Code(Long code);

    List<TbEquipment> findPageByType(EquipmentType type);

    List<TbEquipment> findPageByAddress_CodeAndType(Long code, EquipmentType type);

    List<TbEquipment> findPageByTradeStatus(Boolean tradeStatus);
    // 오래된 순 정렬
    List<TbEquipment> findAllByOrderByCreatedAtDesc();


}