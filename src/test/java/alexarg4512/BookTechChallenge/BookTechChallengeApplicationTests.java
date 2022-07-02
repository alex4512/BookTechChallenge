package alexarg4512.BookTechChallenge;

import alexarg4512.BookTechChallenge.v1.controller.BookController;
import alexarg4512.BookTechChallenge.v1.entity.GutendexResponseEntity;
import alexarg4512.BookTechChallenge.v1.service.GutendexServiceClientImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookTechChallengeApplicationTests {

    /*controllers, services, data repository components*/

    private static final Logger LOGGER = LoggerFactory.getLogger(BookTechChallengeApplicationTests.class);

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    BookTechChallengeApplication statusController;

    @Autowired
    GutendexServiceClientImpl gutendexService;

    @Autowired
    BookController bookController;


    @Value("${gutendex.baseUrl}")
    private String baseUrl;

    @Value("${gutendex.searchUrl}")
    String searchUrl;

    static Map<String, String> params = new HashMap<>();


    static {
        params.put("Accept", "*/*");
        params.put("Content-Type", "application/json");
    }


    @Test
    void testApiAvailable() {
        LOGGER.info("BookTechChallengeApplicationTests:testApiAvailable  - start");
        String result = statusController.getStatus();
        assertNotNull(result);
        assertTrue(result.equalsIgnoreCase("UP"));
        LOGGER.debug("pass!");
        LOGGER.info("BookTechChallengeApplicationTests:testApiAvailable   - done");
    }


    @Test
    void testGutendexServiceAvailable() {
        LOGGER.info("BookTechChallengeApplicationTests:testGutendexServiceAvailable   - start");
        ResponseEntity response = gutendexService.serviceAvailable();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        LOGGER.info("BookTechChallengeApplicationTests:testGutendexServiceAvailable   - done");

    }

    @Test
    void testControllerGetAllBooksNotEmpty() {
        LOGGER.info("BookTechChallengeApplicationTests:testControllerGetAllBooksNotEmpty  - start");
        assertNotNull(bookController.findAll());
        LOGGER.info("BookTechChallengeApplicationTests:testControllerGetAllBooksNotEmpty   - done");

    }


    @Test
    void testControllerFindByNameOfLove() {
        LOGGER.info("BookTechChallengeApplicationTests:testGutendexServiceFindByNameOfLove  - start");
        ResponseEntity response = this.bookController.findByName("Love");
        GutendexResponseEntity responseEntity = (GutendexResponseEntity) response.getBody();
        LOGGER.debug("count : " + responseEntity.getCount());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getCount(), 566);
        LOGGER.info("BookTechChallengeApplicationTests:testGutendexServiceFindByNameOfLove - done");
    }

    @Test
    void test404ErrorStatus() {
        LOGGER.info("BookTechChallengeApplicationTests:test404ErrorStatus  - start");
        HttpClientErrorException e = assertThrows(HttpClientErrorException.class,
                () ->
                        restTemplate.getForObject("http://localhost:8080/v1/lasagna/", GutendexResponseEntity.class, params));
        assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        LOGGER.info("BookTechChallengeApplicationTests:test404ErrorStatus - done");
    }

    /*reference : https://howtodoinjava.com/junit5/expected-exception-example/*/
    @Test
    void test400ErrorStatus() {
        LOGGER.info("BookTechChallengeApplicationTests:test400ErrorStatus  - start");
//	400 example	curl --location --request GET 'http://localhost:8080/v1/books/byName'
		/*{
    "status": 400,
    "message": "Required request parameter 'name' for method parameter type String is not present",
    "timeStamp": 1656749101319
}*/
        HttpClientErrorException e = assertThrows(HttpClientErrorException.class,
                () ->
                        restTemplate.getForObject("http://localhost:8080/v1/books/byName", GutendexResponseEntity.class, params));

        assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);

        LOGGER.info("BookTechChallengeApplicationTests:test400ErrorStatus - done");
    }


    @Test
    void contextLoads() {
    }

}
