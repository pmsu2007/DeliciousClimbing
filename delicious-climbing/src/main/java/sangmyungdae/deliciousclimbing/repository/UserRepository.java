package sangmyungdae.deliciousclimbing.repository;

public interface UserRepository extends JpaRepository<TbUser, Long> {

    Optional<TbUser> findByEmail(String email);
}
