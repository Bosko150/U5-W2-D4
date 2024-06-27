package francescocossu.u5w2d4.services;

import francescocossu.u5w2d4.entities.PostAuthor;
import francescocossu.u5w2d4.exceptions.NotFoundException;
import francescocossu.u5w2d4.payloads.AuthorDTO;
import francescocossu.u5w2d4.repositories.PostAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostAuthorService {

    @Autowired
    private PostAuthorRepository postAuthorRepository;

    public PostAuthor saveAuthor(AuthorDTO authorBody) {

        this.postAuthorRepository.findByEmail(authorBody.email()).ifPresent(author -> {
            throw new IllegalArgumentException("Email already in use");
        });
        PostAuthor newAuthor = new PostAuthor(authorBody.name(), authorBody.surname(), authorBody.email(), authorBody.birthDate());
        return postAuthorRepository.save(newAuthor);
    }

    public Page<PostAuthor> getAllAuthors(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return postAuthorRepository.findAll(pageable);
    }

    public PostAuthor findAuthorById(UUID id) {

        return postAuthorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public PostAuthor findAuthorByIdAndUpdate(UUID id, PostAuthor updatedAuthor) {

        PostAuthor found = this.findAuthorById(id);
        found.setName(updatedAuthor.getName());
        found.setSurname(updatedAuthor.getSurname());
        found.setEmail(updatedAuthor.getEmail());
        found.setBirthDate(updatedAuthor.getBirthDate());
        found.setAvatar(updatedAuthor.getAvatar());
        return this.postAuthorRepository.save(found);
    }

    public void deleteAuthorById(UUID id) {

        postAuthorRepository.deleteById(id);
    }
}
