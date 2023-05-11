package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<TbFile, Long> {

    // 미리보기 이미지
    Optional<TbFile> findFirstByPost_Id(Long postId);
    Optional<TbFile> findFirstByPost(TbPost post);

    // post_id로 조회
    List<TbFile> findByPost_Id(Long postId);
    List<TbFile> findByPost(TbPost post);

    void deleteByPostId(Long postId);
}
