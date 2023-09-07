package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.dto.EquipmentReview;
import sangmyungdae.deliciousclimbing.dto.MateReview;
import sangmyungdae.deliciousclimbing.dto.MateReviewDto;
import sangmyungdae.deliciousclimbing.service.ReviewService;

import java.util.List;

@Controller
@RequestMapping(value = "/Review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //동행 후기 + 거래 후기를 나누는 것이 맞는 것인가.. 는 아닌 것 같아여 한 페이지라
    //조회
    @GetMapping("/{user_id}")
    public String reviewList(@PathVariable String user_id,
                             Model model){
        List<MateReview> mateReviews = reviewService.getMateReviewList();
        List<EquipmentReview> equipmentReviews = reviewService.getEquipmentReviewList();


        return "";
    }

    //생성

    //삭제
}
