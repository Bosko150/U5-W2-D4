package francescocossu.u5w2d4.repositories;

import francescocossu.u5w2d4.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
