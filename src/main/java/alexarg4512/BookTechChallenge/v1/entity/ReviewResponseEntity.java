package alexarg4512.BookTechChallenge.v1.entity;

import java.util.List;

public class ReviewResponseEntity {

    private Book book;

    private List<String> reviews;

    private Double rating;

    public ReviewResponseEntity() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "ReviewResponseEntity{" +
                "book=" + book +
                ", reviews=" + reviews +
                ", rating=" + rating +
                '}';
    }
}
