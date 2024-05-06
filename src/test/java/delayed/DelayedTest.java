package delayed;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DelayedTest {

    HttpClient httpClient;
    URIBuilder endpoint;

    @BeforeEach
    public void setup(){

       
        httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

        endpoint = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users");
              
    }

    @Test
    public void apodTestSuccesfull() throws IOException, InterruptedException, URISyntaxException{
        endpoint.addParameter("delay", "3");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = DelayedRequest.apodRequestDate(httpClient, endpoint);
        DelayedQuestions.validateRequest(HttpStatus.SC_OK, response, "page");
        System.out.println(response.body());
        
                    
    }
 
    @Test
    public void apodTestErrorDate() throws URISyntaxException, IOException, InterruptedException{
        endpoint.addParameter("delay", "3");
        HttpResponse<String> response = DelayedRequest.apodRequestDate(httpClient, endpoint);
        DelayedQuestions.validateRequest(HttpStatus.SC_BAD_REQUEST, response, "To keep ReqRes free, contributions towards server costs are appreciated!");
    }
}
