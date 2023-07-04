package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountainLike;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.dto.famousMountain.*;
import sangmyungdae.deliciousclimbing.repository.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamousMountainService {
    private final FamousMountainRepository famousMountainRepository;
    private final FamousMountainAddressRepository famousMountainAddressRepository;
    private final AddressRepository addressRepository;
    private final FamousMountainLikeRepository famousMountainLikeRepository;
    private final FamousMountainRegionRepository famousMountainRegionRepository;
    private final UserRepository userRepository;

    // 산 목록 조회
    @Transactional
    public List<FamousMountainList> getMountainList(FamousMountainSearchDto dto, Pageable pageable) {

        List<TbFamousMountain> tbFamousMountainList;
        if (dto.getRegion() == null) { // 지역 선택 x,
            tbFamousMountainList = famousMountainRepository.findByNameContaining(dto.getKeyword(), pageable);
        } else { // 지역 선택 o
            // List<TbFamousMountainRegion> 에서 id만 추출하여 list 만듬
            List<Long> ids = famousMountainRegionRepository.findByRegion(dto.getRegion())
                    .stream().map(mountainAddress -> mountainAddress.getFamousMountain().getId())
                    .collect(Collectors.toList());
            tbFamousMountainList = famousMountainRepository.findByNameContainingAndIdIn(dto.getKeyword(), ids, pageable);
        }

        return FamousMountainList.toDtoList(tbFamousMountainList);
    }

    // 산 정보 상세 조회
    @Transactional
    public FamousMountain getMountainDetail(Long famousMountainId, Long userId) {

        TbFamousMountain tbFamousMountain = famousMountainRepository.findById(famousMountainId)
                .orElseThrow(() -> new IllegalArgumentException("tbFamousMountain doesn't exist. famousMountainId=" + famousMountainId));

        // List<TbFamousMountainAddress> -> List<TbAddress>
        List<TbAddress> tbAddressList = tbFamousMountain.getFamousMountainAddresses()
                .stream().map(mountainAddress -> mountainAddress.getAddress()).collect(Collectors.toList());

        // List<TbAddress>를 이용하여 상세주소 문자열로 변환 ex) "서울특별시 강북구ㆍ성북구ㆍ종로구ㆍ은평구, 경기도 고양시ㆍ양주시" 형식
        String detailRegion = convertToDetailRegionString(tbAddressList);

        Boolean likeCheck = likeCheckProcessing(famousMountainId, userId);

        return FamousMountain.builder()
                .entity(tbFamousMountain)
                .detailRegion(detailRegion)
                .likeCheck(likeCheck)
                .build();
    }

//TODO: like test
    @Transactional
    public void insertLike(Long famousMountainId, Long userId) {

        TbUser tbUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("tbUser doesn't exist. userId=" + userId));
        TbFamousMountain tbFamousMountain = famousMountainRepository.findById(famousMountainId).orElseThrow(() -> new IllegalArgumentException("tbFamousMountain doesn't exist. famousMountainId=" + famousMountainId));

        if (famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(userId, famousMountainId).isPresent()) {
            throw new IllegalStateException("tbFamousMountainLike already exist. famousMountainId=" + famousMountainId + ", userId=" + userId);
        }

        famousMountainLikeRepository.save(TbFamousMountainLike.builder()
                .famousMountain(tbFamousMountain)
                .user(tbUser)
                .build());
        tbFamousMountain.updateLike(tbFamousMountain.getLikes() + 1);
    }

    @Transactional
    public void deleteLike(Long famousMountainId, Long userId) {

        TbFamousMountain tbFamousMountain = famousMountainRepository.findById(famousMountainId).orElseThrow(() -> new IllegalArgumentException("tbFamousMountain doesn't exist. famousMountainId=" + famousMountainId));


        TbFamousMountainLike tbFamousMountainLike = famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(userId, famousMountainId)
                .orElseThrow(() -> new IllegalArgumentException("tbFamousMountainLike doesn't exist. famousMountainId=" + famousMountainId + ", userId=" + userId));

        famousMountainLikeRepository.delete(tbFamousMountainLike);
        tbFamousMountain.updateLike(tbFamousMountain.getLikes() - 1);
    }

    // 산 추천
    @Transactional
    public List<FamousMountain> getRecommendFamousMountains(RecommendDto dto) {

        return null;
    }

    /**
     * List<TbAddress>를 이용하여 상세주소 문자열로 변환
     * ex) "서울특별시 강북구ㆍ성북구ㆍ종로구ㆍ은평구, 경기도 고양시ㆍ양주시" 형식
     * @param tbAddressList
     * @return
     */
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


    private Boolean likeCheckProcessing(Long famousMountainId, Long userId) {

        if (userId == null) {
            return false;
        } else {
            Optional<TbFamousMountainLike> famousMountainLike = famousMountainLikeRepository.findByUser_IdAndFamousMountain_Id(userId, famousMountainId);
            return famousMountainLike.isPresent() ? true : false;
        }
    }

}