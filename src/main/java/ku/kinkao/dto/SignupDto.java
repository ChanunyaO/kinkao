package ku.kinkao.dto;

import ku.kinkao.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$",
            message = "First name can only contain letters")
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$",
            message = "Role is in an incorrect format.")
    private String role;
}
