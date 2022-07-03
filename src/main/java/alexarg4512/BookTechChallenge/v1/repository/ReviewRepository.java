package alexarg4512.BookTechChallenge.v1.repository;

import alexarg4512.BookTechChallenge.v1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    public List<Review> findAllByBookId(Long id);
}
