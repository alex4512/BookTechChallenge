package alexarg4512.BookTechChallenge.v1.controller;

import alexarg4512.BookTechChallenge.v1.service.GutendexServiceClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
    GutendexServiceClientImpl gutendexService;

    static List lstBooks;

    static {
        lstBooks = new ArrayList<String>();
        lstBooks.add("Data Mining and Algorithms");
        lstBooks.add("Machine Learning Algorithms");
        lstBooks.add(null);
        lstBooks.add("Machine Learning Algorithms");
    }

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
