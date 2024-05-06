package nasa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpResponse;

import org.apache.http.HttpStatus;

public class ApodQuestions {
    public static void validateRequest(int statusCode, HttpResponse<String> response, String bodyContent){
        assertEquals(HttpStatus.SC_OK, response.statusCode(),"correct user 2");
        assertTrue(response.body().contains("page"), "complete page");
    }
}
