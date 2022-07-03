package alexarg4512.BookTechChallenge.v1.service;

import alexarg4512.BookTechChallenge.v1.entity.Review;
import org.springframework.http.ResponseEntity;

public interface ReviewService {

    public ResponseEntity addReview(Review review);

    public ResponseEntity getReviews();

    ResponseEntity getReviewsById(Long id);
}
