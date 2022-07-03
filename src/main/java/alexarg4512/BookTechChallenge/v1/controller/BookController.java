package alexarg4512.BookTechChallenge.v1.controller;

import alexarg4512.BookTechChallenge.v1.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService gutendexService;

     @GetMapping("/")
    public String findAll() {
        LOGGER.info("finaAll enter method");

        return String.valueOf(gutendexService.serviceAvailable());

    }

    @GetMapping("/byName")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        return gutendexService.findAllByName(name);
    }


}
