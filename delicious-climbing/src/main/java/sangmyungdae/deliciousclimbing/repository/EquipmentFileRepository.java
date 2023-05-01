package sangmyungdae.deliciousclimbing.repository;

public interface EquipmentFileRepository extends JpaRepository<TbEquipmentFile, Long> {

    Optional<TbEquipmentFile> findFirstByPostId(Long postId);

    Optional<TbEquipmentFile> findFirstByTbPost(TbPost post);

    List<TbEquipmentFile> findByPostId(Long postId);

    List<TbEquipmentFile> findByTbPost(TbPost post);
}