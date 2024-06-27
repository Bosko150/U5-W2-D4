package francescocossu.u5w2d4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public record AuthorDTO(@NotEmpty(message = "name cannot be empty")
                        @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
                        String name,
                        @NotEmpty(message = "surname cannot be empty")
                        @Size(min = 3, max = 20, message = "surname must be between 3 and 20 characters")
                        String surname,
                        @NotEmpty(message = "email cannot be empty")
                        @Email
                        String email,
                        @NotNull(message = "birthDate cannot be null")
                        Date birthDate) {
}
