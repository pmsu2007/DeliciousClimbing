package sangmyungdae.deliciousclimbing.repository;

public interface MateCommentRepository extends JpaRepository<TbMateComment, Long> {

    // postId로 생성날짜 기준 오름차순 정렬하여 댓글 리스트 조회
    List<TbMateComment> findByPostIdOrderByCreatedAt(Long PostId);

    List<TbMateComment> findByTbPostOrderByCreatedAt(TbPost post);
}