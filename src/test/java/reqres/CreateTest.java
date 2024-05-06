package reqres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateTest {

    HttpClient httpClient;
    URIBuilder endpoint;

    @BeforeEach
    public void setup(){

        httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
        
        endpoint =  new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users");

    }

    @Test
    public void createUser() throws URISyntaxException, IOException, InterruptedException{

        String requestBody = "{ 'name': 'romeo','job': 'QA full'}";
        HttpRequest request =  HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(requestBody))
            .uri(endpoint.build())
            .build();
        
        HttpResponse<String> response =  httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("RESPONSE BODY: " + response.body() );
        assertEquals(HttpStatus.SC_CREATED, response.statusCode(), "Validación de Status Code Created");
        assertTrue(response.body().contains("romeo"),"nombre validado");
        assertTrue(response.body().contains("QA full"),"Job validado");
    }
    
}
