package sangmyungdae.deliciousclimbing.repository;

public interface PostRepository extends JpaRepository<TbPost, Long> {

    // 게시판 종류로 조회
    Page<TbPost> findPageByType(BoardType type, Pageable pageable);

    // 검색 조회
    Page<TbPost> findPageByTypeAndTitleContaining(BoardType type, String searchKeyword, Pageable pageable);

    // 내가쓴글 페이징
    Page<TbPost> findPageByUserId(Long userId, Pageable pageable);

    Page<TbPost> findPageByTbUser(TbUser user, Pageable pageable);
}