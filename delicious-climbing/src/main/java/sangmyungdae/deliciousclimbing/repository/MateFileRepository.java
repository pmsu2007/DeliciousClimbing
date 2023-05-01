package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface MateFileRepository extends JpaRepository<TbMateFile, Long> {

    Optional<TbMateFile> findFirstByPostId(Long postId);

    Optional<TbMateFile> findFirstByTbPost(TbPost post);

    List<TbMateFile> findByPostId(Long postId);

    List<TbMateFile> findByTbPost(TbPost post);
}