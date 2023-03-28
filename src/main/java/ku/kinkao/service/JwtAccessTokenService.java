package ku.kinkao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtAccessTokenService {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Autowired
    private RestTemplate restTemplate;

    private String token = null;

    public String requestAccessToken() {

        if (token != null)
            return token;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",
                MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody =
                new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("audience", audience);

        HttpEntity entity = new HttpEntity(requestBody, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(issuer + "oauth/token",
                        HttpMethod.POST,
                        entity, String.class);

        String jwtResponse = response.getBody();

        return jwtResponse;
    }
}
