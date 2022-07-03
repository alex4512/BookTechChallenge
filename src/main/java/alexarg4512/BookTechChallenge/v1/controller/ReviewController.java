package alexarg4512.BookTechChallenge.v1.controller;

import alexarg4512.BookTechChallenge.v1.entity.Review;
import alexarg4512.BookTechChallenge.v1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

//    @GetMapping("/review")
//    public ResponseEntity findAllReviews() {
//        ResponseEntity response = reviewService.getReviews();
//        return  response;
//    }

    @PostMapping("/review")
    public ResponseEntity addReview(@RequestBody Review review) { return reviewService.addReview(review);   }

    @GetMapping("/review/{id}")
    public ResponseEntity getReview(@PathVariable Long id) { return reviewService.getReviewsById(id);   }

}
