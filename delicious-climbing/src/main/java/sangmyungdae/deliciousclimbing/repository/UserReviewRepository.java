package sangmyungdae.deliciousclimbing.repository;

public interface UserReviewRepository extends JpaRepository<TbUserReview, Long> {

    List<TbUserReview> findByUserId(Long userId);

    List<TbUserReview> findByTbUser(TbUser user);
    //UserReivew findByReviewId(Long reviewId);
    // UserReview findByReview(Review review);
}
