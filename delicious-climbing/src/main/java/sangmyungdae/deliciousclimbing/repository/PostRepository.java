package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

public interface PostRepository extends JpaRepository<TbPost, Long> {

    // 게시판 종류로 조회
    Page<TbPost> findPageByType(BoardType type, Pageable pageable);

    // 검색 조회
    Page<TbPost> findPageByTypeAndTitleContaining(BoardType type, String searchKeyword, Pageable pageable);

    // 내가쓴글 페이징
    Page<TbPost> findPageByUser_Id(Long userId, Pageable pageable);
    Page<TbPost> findPageByUser(TbUser user, Pageable pageable);
}