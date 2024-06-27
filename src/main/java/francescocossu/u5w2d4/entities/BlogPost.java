package francescocossu.u5w2d4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String body;
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "authorID")
    private PostAuthor author;

    public BlogPost(String category, String title, String body, int readingTime, PostAuthor author) {
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/200/300";
        this.body = body;
        this.readingTime = readingTime;
        this.author = author;
    }
}
