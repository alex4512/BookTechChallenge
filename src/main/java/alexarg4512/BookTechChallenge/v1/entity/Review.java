package alexarg4512.BookTechChallenge.v1.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity

@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue
    @Column
    @JsonIgnore
    Long reviewId;
    @JsonProperty("id")
    @Column
    Long bookId;
    @Column
    Long rating;
    @Column
    String review;


    public Review() {
    }

    //    used for memory review simulator
    public Review(Long bookId, Long rating, String review) {
        this.bookId = bookId;
        this.rating = rating;
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + bookId +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Review(Long reviewId, Long bookId, Long rating, String review) {
        this.reviewId = reviewId;
        this.bookId = bookId;
        this.rating = rating;
        this.review = review;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
