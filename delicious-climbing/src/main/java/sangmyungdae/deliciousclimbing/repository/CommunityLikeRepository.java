package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbCommunityLike;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;

import java.util.List;
import java.util.Optional;

public interface CommunityLikeRepository extends JpaRepository<TbCommunityLike, Long> {
    List<TbCommunityLike> findByPost_Id(Long postId);
    List<TbCommunityLike> findByPost(TbPost post);

    List<TbCommunityLike> findByUser_Id(Long userId);
    List<TbCommunityLike> findByUser(TbUser user);
    Optional<TbCommunityLike> findByUserAndPost(TbUser user, TbPost post);

}
