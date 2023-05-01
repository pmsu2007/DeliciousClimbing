package sangmyungdae.deliciousclimbing.repository;

public interface ReviewRepository extends JpaRepository<TbReview, Long> {

    TbReview findByIdAndType(Long id, ReivewType type);
}
