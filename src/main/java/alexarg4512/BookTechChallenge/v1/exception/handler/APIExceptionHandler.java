package alexarg4512.BookTechChallenge.v1.exception.handler;

import alexarg4512.BookTechChallenge.v1.exception.BookNotFoundException;
import alexarg4512.BookTechChallenge.v1.exception.ReviewNotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;


@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class, ReviewNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handle404Exception(Exception exc) {

        JSONObject jsonResponseBody = new JSONObject();
        jsonResponseBody.put("status",
                "404");
        jsonResponseBody.put("message",
                "The resource you requested was not found on the server," +
                        " or the server will not disclose that such a resource exists");
        return new ResponseEntity(jsonResponseBody, HttpStatus.NOT_FOUND);
    }


    // add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler({HttpClientErrorException.class, Throwable.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleGenericException(Exception exc) {

        JSONObject jsonResponseBody = new JSONObject();
        jsonResponseBody.put("status",
                "400");
        jsonResponseBody.put("message",
                exc.getMessage());
        return new ResponseEntity(jsonResponseBody, HttpStatus.BAD_REQUEST);
    }
}


