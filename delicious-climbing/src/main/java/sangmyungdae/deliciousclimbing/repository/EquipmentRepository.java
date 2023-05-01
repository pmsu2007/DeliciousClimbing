package sangmyungdae.deliciousclimbing.repository;

public interface EquipmentRepository extends JpaRepository<TbEquipment, Long> {

    Page<TbEquipment> findPageByAddressId(Long id, Pageable pageable);

    Page<TbEquipment> findPageByAddressIdAndType(Long id, EquipmentType type, Pageable pageable);

    Page<TbEquipment> findPageByAddressIdAndTypeAndTrade(Long id, EquipmentType type, Boolean trade, Pageable pageable);

    Page<TbEquipment> findPageByTrade(Boolean trade, Pageable pageable);

}