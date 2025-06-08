package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequestScoped
public class TokenService {

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @Inject
    @ConfigProperty(name = "keycloak.credentials.client-id")
    String clientId;

    @Inject
    @ConfigProperty(name = "keycloak.credentials.secret", defaultValue = "")
    String clientSecret;

    public String getAccessToken(String userName, String password)
            throws IOException, InterruptedException {

        String keycloakTokenEndpoint = issuer + "/protocol/openid-connect/token";

        StringBuilder requestBody = new StringBuilder();
        requestBody.append("username=").append(userName)
                .append("&password=").append(password)
                .append("&grant_type=password")
                .append("&client_id=").append(clientId);

        if (!clientSecret.isBlank()) {
            requestBody.append("&client_secret=").append(clientSecret);
        }

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .uri(URI.create(keycloakTokenEndpoint))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body()).get("access_token").textValue();
        } else {
            throw new UnauthorizedException("Token request failed: " + response.body());
        }
    }
}
