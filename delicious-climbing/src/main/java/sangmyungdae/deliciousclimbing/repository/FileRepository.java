package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<TbFile, Long> {

    // 미리보기 이미지
    Optional<TbFile> findFirstByPostId(Long postId);

    Optional<TbFile> findFirstByTbPost(TbPost post);

    List<TbFile> findByPostId(Long postId);

    List<TbFile> findByTbPost(TbPost post);
}
