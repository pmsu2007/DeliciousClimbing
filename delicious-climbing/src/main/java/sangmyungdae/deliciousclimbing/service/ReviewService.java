package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;
import sangmyungdae.deliciousclimbing.dto.review.Review;
import sangmyungdae.deliciousclimbing.dto.review.ReviewDto;
import sangmyungdae.deliciousclimbing.repository.*;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbUser findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> ExceptionUtil.id(id, TbUser.class.getName()));
    }

    private TbReview findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> ExceptionUtil.id(reviewId, TbReview.class.getName()));
    }

    @Transactional
    public Review getReview(Long reviewId) {
        TbReview review = findReview(reviewId);

        return Review.builder()
                .entity(review)
                .build();
    }
    //리뷰 조회
    @Transactional
    public List<Review> getReviewList(ReviewType type){
        List<TbReview> reviews = reviewRepository.findByType(type);
        return Review.toDtoList(reviews);
    }
    //리뷰 생성
    @Transactional
    public Review creatReview(Long userId, ReviewDto dto){
        TbUser user = findUser(userId);
        TbUser reviewer = findUser(AuthUtil.getAuthUser());

        if (user == reviewer) {
            throw ExceptionUtil.available("본인에게 리뷰를 작성할 수 없습니다");
        }

        TbReview review = TbReview.builder()
                .type(dto.getType())
                .content(dto.getContent())
                .reviewer(reviewer)
                .user(user)
                .build();

        return Review.builder()
                .entity(reviewRepository.save(review))
                .build();
    }
    //리뷰 삭제
    @Transactional
    public void deleteReview(Long reviewId){
        TbReview review = findReview(reviewId);
        reviewRepository.delete(review);
    }
}
