package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbMate;
import sangmyungdae.deliciousclimbing.domain.entity.TbMateFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;
import java.util.Optional;

public interface MateFileRepository extends JpaRepository<TbMateFile, Long> {

    Optional<TbMateFile> findFirstByMate_Id(Long mateId);

    Optional<TbMateFile> findFirstByMate(TbMate mate);

    List<TbMateFile> findByMate_Id(Long mateId);

    List<TbMateFile> findByMate(TbMate mate);
}