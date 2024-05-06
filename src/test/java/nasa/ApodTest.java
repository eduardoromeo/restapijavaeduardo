package nasa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import utils.JsonValidator;

public class ApodTest {

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
        endpoint.addParameter("page", "2");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = ApodRequest.apodRequestDate(httpClient, endpoint);
        ApodQuestions.validateRequest(HttpStatus.SC_OK, response, "page");
        System.out.println(response.body());
        
        
       // String validacion = JsonValidator.validateJson(response.body(),"ApodRequest.json");
      //  assertEquals("", validacion, "Validacion del Json Schema");
      
    }
 
    @Test
    public void apodTestErrorDate() throws URISyntaxException, IOException, InterruptedException{
        endpoint.addParameter("page", "10");
        HttpResponse<String> response = ApodRequest.apodRequestDate(httpClient, endpoint);
        ApodQuestions.validateRequest(HttpStatus.SC_BAD_REQUEST, response, "To keep ReqRes free, contributions towards server costs are appreciated!");
    }
}
