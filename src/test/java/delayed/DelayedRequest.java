package delayed;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.http.client.utils.URIBuilder;

public class DelayedRequest {

    public static HttpResponse<String> apodRequestDate(HttpClient httpClient, URIBuilder endpoint) throws URISyntaxException, IOException, InterruptedException{

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(endpoint.build())
            .build();

        return httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

    }
    
}
