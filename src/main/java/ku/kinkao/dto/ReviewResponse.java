package ku.kinkao.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class ReviewResponse {
    private String username;
    private String reviewText;
    private Instant createdAt;
}
