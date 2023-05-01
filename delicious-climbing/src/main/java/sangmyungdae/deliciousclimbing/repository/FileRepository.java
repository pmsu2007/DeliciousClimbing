package sangmyungdae.deliciousclimbing.repository;

public interface FileRepository extends JpaRepository<TbFile, Long> {

    // 미리보기 이미지
    Optional<TbFile> findFirstByPostId(Long postId);

    Optional<TbFile> findFirstByTbPost(TbPost post);

    List<TbFile> findByPostId(Long postId);

    List<TbFile> findByTbPost(TbPost post);
}
