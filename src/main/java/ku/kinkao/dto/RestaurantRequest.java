package ku.kinkao.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    @Range(min=1, max=5)
    private Integer rating;
}