package sangmyungdae.deliciousclimbing.repository;

public interface CommentRepository extends JpaRepository<TbComment, Long> {

    // postId로 생성날짜 기준 오름차순 정렬하여 댓글 리스트 조회
    List<TbComment> findByPostIdOrderByCreatedAt(Long PostId);

    List<TbComment> findbyTbPostOrderByCreatedAt(TbPost post);
}