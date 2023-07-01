package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.dto.EquipmentReview;
import sangmyungdae.deliciousclimbing.dto.EquipmentReviewDto;
import sangmyungdae.deliciousclimbing.dto.MateReview;
import sangmyungdae.deliciousclimbing.dto.MateReviewDto;
import sangmyungdae.deliciousclimbing.repository.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final EquipmentReviewRepository equipmentReviewRepository;
    private final MateReviewRepository mateReviewRepository;
    private final EquipmentRepository equipmentRepository;
    private final MateRepository mateRepository;
    private  final UserRepository userRepository;
    //리뷰 조회
    @Transactional
    public List<EquipmentReview> getEquipmentReviewList(){
        List<TbEquipmentReview> reviews = equipmentReviewRepository.findAll();

        List<EquipmentReview> EquipmentReviwList =
                reviews.stream().map(review -> new EquipmentReview(review))
                        .collect(Collectors.toList());

        return EquipmentReviwList;
    }
    @Transactional
    public List<MateReview> getMateReviewList(){
        List<TbMateReview> reviews = mateReviewRepository.findAll();

        List<MateReview> MateReviwList =
                reviews.stream().map(review -> new MateReview(review))
                        .collect(Collectors.toList());

        return MateReviwList;
    }

    //리뷰 생성
    @Transactional
    public EquipmentReview createEquipmentReview(EquipmentReviewDto dto){
        TbEquipment equipment = equipmentRepository.findById(dto.getPostId()).orElseThrow();
        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();

        TbEquipmentReview entity = equipmentReviewRepository.save(dto.toEntity(equipment,user));

        return EquipmentReview.builder()
                .entity(entity)
                .build();
    }
    @Transactional
    public MateReview createMateReview(MateReviewDto dto) {
        TbMate mate = mateRepository.findById(dto.getPostId()).orElseThrow();
        TbUser user = userRepository.findById(dto.getUserId()).orElseThrow();

        TbMateReview entity = mateReviewRepository.save(dto.toEntity(mate, user));

        return MateReview.builder()
                .entity(entity)
                .build();
    }
    //리뷰 삭제
    @Transactional
    public void deleteEquipmentReview(Long id){
        equipmentReviewRepository.deleteById(id);
    }
    @Transactional
    public void deleteMateReview(Long id){
        mateReviewRepository.deleteById(id);
    }


}
