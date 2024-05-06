package usernotfound;

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

public class UsernotFoundTest {

    private HttpClient httpClient;
    private URI endpoint;

    @BeforeEach
    public void setup() throws URISyntaxException {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        endpoint = new URI("https://reqres.in/api/users/1000"); // Cambiar el ID del usuario
    }

    @Test
    public void testGetNonExistingUser() throws IOException, InterruptedException {
        // Realizar la solicitud GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar el código de estado
        assertEquals(404, response.statusCode()); // Esperamos un código 404 para un usuario inexistente

        // Imprimir el cuerpo de la respuesta
        System.out.println("Respuesta del servidor: " + response.body());
    }
}