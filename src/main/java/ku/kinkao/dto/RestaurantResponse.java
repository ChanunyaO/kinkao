package ku.kinkao.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantResponse {
    private UUID id;
    private String name;
    private String address;
    private int rating;
}
