package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;
import sangmyungdae.deliciousclimbing.dto.review.Review;
import sangmyungdae.deliciousclimbing.dto.review.ReviewDto;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.service.ReviewService;
import sangmyungdae.deliciousclimbing.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    @GetMapping("/{userId}")
    public String reviewListPage(@PathVariable Long userId,
                                 @RequestParam ReviewType type,
                                 ReviewDto reviewDto,
                                 Model model){
        User user = userService.getUserById(userId);
        List<Review> reviews = reviewService.getReviewList(type);

        model.addAttribute("types", ReviewType.values());
        model.addAttribute("reviewDto", reviewDto);
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);

        return "reviewListPage";
    }

    @PostMapping("/{userId}/create")
    public String createReview(@ModelAttribute ReviewDto reviewDto,
                               @PathVariable Long userId,
                               RedirectAttributes redirectAttributes) {
        Review review = reviewService.creatReview(userId, reviewDto);
        redirectAttributes.addAttribute("type", review.getType());
        return "redirect:/reviews/" + userId;
    }

    @PostMapping("{userId}/delete/{reviewId}")
    public String deleteReview(@PathVariable Long userId,
                               @PathVariable Long reviewId,
                               RedirectAttributes redirectAttributes) {
        Review review = reviewService.getReview(reviewId);
        reviewService.deleteReview(reviewId);
        redirectAttributes.addAttribute("type", review.getType());
        return "redirect:/reviews/" + userId;
    }

}
