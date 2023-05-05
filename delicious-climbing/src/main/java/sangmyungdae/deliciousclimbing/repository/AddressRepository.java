package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

public interface AddressRepository extends JpaRepository<TbAddress, Long> {

//    TbAddress findByIdAndSido(Long id, String sido);
//
//    TbAddress findByIdAndSidoAndSigungu(Long id, String Sido, String sigungu);
//
//    TbAddress findByIdAndSidoAndSigunguAndCode(Long id, String sido, String sigungu, String code);
    TbAddress findByCode(Long code);
}
