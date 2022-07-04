package alexarg4512.BookTechChallenge.v1.service;

import alexarg4512.BookTechChallenge.v1.entity.Book;
import alexarg4512.BookTechChallenge.v1.entity.Review;
import alexarg4512.BookTechChallenge.v1.entity.ReviewResponseEntity;
import alexarg4512.BookTechChallenge.v1.exception.ReviewNotFoundException;
import alexarg4512.BookTechChallenge.v1.repository.ReviewRepository;
import net.minidev.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    BookService bookService;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public ResponseEntity addReview(Review review) {
        ResponseEntity bookLookupResponse = bookService.findById(review.getBookId());
        if(review.getRating() > 5 || review.getRating() < 1) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Rating value should be 1-5 (inclusive)");
        }
        Review reviewResult = reviewRepository.save(review);
        JSONObject jsonObject = new JSONObject(); // TODO - refactor, not a good practice
        jsonObject.put("message", "created");
        jsonObject.put("reviewId", reviewResult.getReviewId());
        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity getReviews() {
        return new ResponseEntity<>(reviewRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getReviewsById(Long id) {
        ResponseEntity bookResponse = this.bookService.findById(id);
        if(bookResponse != null && bookResponse.getStatusCode()==HttpStatus.OK)
        {
            List<Review> reviewJPAResultList = (List<Review>) reviewRepository.findAllByBookId(id);
            if(reviewJPAResultList != null && reviewJPAResultList.size()>0) {
                 ReviewResponseEntity reviewResponseEntity = new ReviewResponseEntity();
                 reviewResponseEntity.setReviews(reviewJPAResultList.stream()
                         .map(Review::getReview)
                         .collect(Collectors.toList()));
                 reviewResponseEntity.setRating(reviewJPAResultList.stream()
                         .mapToDouble(Review::getRating)
                         .average()
                         .orElse(0.0));
                 reviewResponseEntity.setBook((Book)bookResponse.getBody());
                 return new ResponseEntity<>(reviewResponseEntity, HttpStatus.OK);
            } else {
                throw new ReviewNotFoundException("Review not found!");
            }

        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "The server couldn't find any reviews for that book id");
    }
}
