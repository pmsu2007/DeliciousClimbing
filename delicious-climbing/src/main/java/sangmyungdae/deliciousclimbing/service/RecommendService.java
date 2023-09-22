package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.dto.recommend.RecommendMountain;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final FamousMountainRepository famousMountainRepository;


    @Transactional
    public List<RecommendMountain> findMountainRand(){

        List<TbFamousMountain> randMountainList = famousMountainRepository.findAllRand();
        List<RecommendMountain> randMountainDtoList = new ArrayList<>();
        for(int i = 0; i<3; i++) {
            // List<TbFamousMountainAddress> -> List<TbAddress>
            System.out.println("randMountainList.get(i).getFamousMountainAddresses() = " + randMountainList.get(i).getFamousMountainAddresses());
            System.out.println("randMountainList.get(i).getName() = " + randMountainList.get(i).getName());
            List<TbAddress> tbAddressList = randMountainList.get(i).getFamousMountainAddresses()
                    .stream().map(mountainAddress -> mountainAddress.getAddress()).collect(Collectors.toList());

            // List<TbAddress>를 이용하여 상세주소 문자열로 변환 ex) "서울특별시 강북구ㆍ성북구ㆍ종로구ㆍ은평구, 경기도 고양시ㆍ양주시" 형식
            String detailRegion = convertToDetailRegionString(tbAddressList);
            System.out.println("detailRegion = " + detailRegion);
            RecommendMountain dto = RecommendMountain.builder()
                                        .entity(randMountainList.get(i))
                                        .detailRegion(detailRegion)
                                        .build();
            randMountainDtoList.add(dto);
        }

        return randMountainDtoList;
    }

    private String convertToDetailRegionString(List<TbAddress> tbAddressList) {

        tbAddressList.sort(Comparator.comparing(TbAddress::getCode)); // 주소 코드 기준 오름차순 정렬
        StringBuilder detailRegion = new StringBuilder();

        for (int i = 0; i < tbAddressList.size(); i++) {
            if (i == 0) {
                detailRegion.append(tbAddressList.get(i).getSido())
                        .append(" ")
                        .append(tbAddressList.get(i).getSigungu());
            } else {
                if(tbAddressList.get(i).getSido() == tbAddressList.get(i-1).getSido())
                    detailRegion.append("ㆍ")
                            .append(tbAddressList.get(i).getSigungu());
                else
                    detailRegion.append(", ")
                            .append(tbAddressList.get(i).getSido())
                            .append(" ")
                            .append(tbAddressList.get(i).getSigungu());
            }
        }

        return detailRegion.toString();
    }


}
