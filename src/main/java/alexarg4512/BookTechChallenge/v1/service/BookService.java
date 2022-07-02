package alexarg4512.BookTechChallenge.v1.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

     public ResponseEntity serviceAvailable();

    public List findOne(String name);

    public ResponseEntity<?> findAllByName(String name);
}


