package userid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTestDoce {

    private HttpClient httpClient;
    private URI endpoint;

    @BeforeEach
    public void setup() throws URISyntaxException {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        endpoint = new URI("https://reqres.in/api/users/12");
    }

    @Test
    public void testGetUserById() throws IOException, InterruptedException {
        // Realizar la solicitud GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar el código de estado
        assertEquals(200, response.statusCode());

        // Imprimir el ID del usuario 12
        String responseBody = response.body();
        int id = Integer.parseInt(responseBody.substring(responseBody.indexOf("id") + 4, responseBody.indexOf("id") + 6));
        System.out.println("ID del usuario 12: " + id);
    }
}