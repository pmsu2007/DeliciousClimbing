package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.dto.recommend.RecommendMountain;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final FamousMountainRepository famousMountainRepository;

    @Transactional
    public List<RecommendMountain> findMountainRand(){

        List<TbFamousMountain> randMountainList = famousMountainRepository.findAllRand();

        return RecommendMountain.toDtoList(randMountainList);
    }
}
