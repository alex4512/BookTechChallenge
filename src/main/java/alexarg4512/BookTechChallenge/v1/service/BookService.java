package alexarg4512.BookTechChallenge.v1.service;

import org.springframework.http.ResponseEntity;

public interface BookService {

    public ResponseEntity serviceAvailable();

    public ResponseEntity findById(Long id);

    public ResponseEntity<?> findAllByName(String name);
}


