package ku.kinkao.service;

import ku.kinkao.dto.ReviewRequest;
import ku.kinkao.dto.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    public ReviewRequest createReview(ReviewRequest review) {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(review,headers);

        String url = "http://localhost:8091/api/review";

        ResponseEntity<ReviewRequest> response =
                restTemplate.exchange(url, HttpMethod.POST,
                        entity, ReviewRequest.class);

        return response.getBody();
    }

    public List<ReviewResponse> getRestaurantReview(UUID restaurantId) {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8091/api/review/" + restaurantId;

        ResponseEntity<ReviewResponse[]> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        entity, ReviewResponse[].class);

        ReviewResponse[] reviews = response.getBody();
        return Arrays.asList(reviews);
    }
}
