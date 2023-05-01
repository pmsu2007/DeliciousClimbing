package sangmyungdae.deliciousclimbing.repository;

public interface MateFileRepository extends JpaRepository<TbMateFile, Long> {

    Optional<TbMateFile> findFirstByPostId(Long postId);

    Optional<TbMateFile> findFirstByTbPost(TbPost post);

    List<TbMateFile> findByPostId(Long postId);

    List<TbMateFile> findByTbPost(TbPost post);
}