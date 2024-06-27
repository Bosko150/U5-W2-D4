package francescocossu.u5w2d4.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BlogPostDTO(
        @NotEmpty(message = "category cannot be empty")
        @Size(min = 3, max = 15, message = "category must be between 3 and 15 characters")
        String category,
        @NotEmpty(message = "title cannot be empty")
        @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
        String title,
        @NotEmpty(message = "body cannot be empty")
        @Size(min = 4, max = 2000, message = "body must be between 3 and 2000 characters")
        String body,
        @NotNull(message = "readingTime cannot be null")
        int readingTime,
        @NotNull(message = "authorId cannot be null")
        UUID authorId) {
}
