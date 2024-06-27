package francescocossu.u5w2d4.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class BlogPostPayload {
    private String category;
    private String title;
    private String cover;
    private String body;
    private int readingTime;
    private UUID authorId;
}
