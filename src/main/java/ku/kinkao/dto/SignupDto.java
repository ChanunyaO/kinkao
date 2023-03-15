package ku.kinkao.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;

    @NotBlank
    @Size(min=12, max=128, message = "Password must have at least 12 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank
    private String lastName;
}
