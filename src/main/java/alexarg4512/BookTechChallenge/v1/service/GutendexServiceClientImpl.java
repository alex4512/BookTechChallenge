package alexarg4512.BookTechChallenge.v1.service;

import alexarg4512.BookTechChallenge.v1.entity.Book;
import alexarg4512.BookTechChallenge.v1.entity.GutendexResponseEntity;
import alexarg4512.BookTechChallenge.v1.exception.BookNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Service
public class GutendexServiceClientImpl implements BookService {

   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GutendexServiceClientImpl.class);
   static Map<String, String> params = new HashMap<>();

   static {
       params.put("Accept", "*/*");
       params.put("Content-Type","application/json");
   }

    @Autowired
    public RestTemplate restTemplate;

    @Value("${gutendex.baseUrl}")
    private String baseUrl;

    @Value("${gutendex.idsUrl}")
    String idsUrl;

    @Value("${gutendex.searchUrl}")
    String searchUrl;

    public ResponseEntity serviceAvailable() {

        LOGGER.debug("baseUrl : " + baseUrl);
        String response = null;

        try {
            response = restTemplate.getForObject(baseUrl, String.class, params);

        } catch (Exception e) {
            LOGGER.error("error fetching books : " + e.getMessage() + " " + e.getStackTrace());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity findById(Long id) {

        GutendexResponseEntity gutendexResponseEntity = restTemplate.getForObject(baseUrl+idsUrl+id, GutendexResponseEntity.class, params);
        LOGGER.info("List size : " + gutendexResponseEntity.getResults().size());
        if(gutendexResponseEntity!=null && gutendexResponseEntity.getCount()==1)
        {
            return new ResponseEntity<Book>(gutendexResponseEntity.getResults().iterator().next(), HttpStatus.OK);
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found. Cannot add review");
        }
    }

    @Override
    public ResponseEntity<?> findAllByName(String name) {

        GutendexResponseEntity gutendexResponseEntity = restTemplate.getForObject(baseUrl+searchUrl+name, GutendexResponseEntity.class, params);
        LOGGER.debug("result count : " + gutendexResponseEntity.getCount());
        LOGGER.debug("List size : " + gutendexResponseEntity.getResults().size());
        return new ResponseEntity<GutendexResponseEntity>(gutendexResponseEntity, HttpStatus.OK);
    }
}
