package sangmyungdae.deliciousclimbing.repository;

public interface AddressRepository extends JpaRepository<TbAddress, Long> {

    TbAddress findByIdAndSido(Long id, String sido);

    TbAddress findByIdAndSidoAndSigungu(Long id, String Sido, String sigungu);

    TbAddress findByIdAndSidoAndSigunguAndCode(Long id, String sido, String sigungu, String code);
    // Address findByCode(Long code);
}
