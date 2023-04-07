package ku.kinkao.model;

import ku.kinkao.config.AttributeEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = AttributeEncryptor.class)
    private String username;

    private String password;

    @Convert(converter = AttributeEncryptor.class)
    private String firstName;

    @Convert(converter = AttributeEncryptor.class)
    private String lastName;

    @Convert(converter = AttributeEncryptor.class)
    private String email;
    private Instant createdAt;
    private String role;
}
