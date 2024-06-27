package francescocossu.u5w2d4.controllers;

import francescocossu.u5w2d4.entities.PostAuthor;
import francescocossu.u5w2d4.exceptions.BadRequestException;
import francescocossu.u5w2d4.payloads.AuthorDTO;
import francescocossu.u5w2d4.services.PostAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class PostAuthorController {

    @Autowired
    private PostAuthorService authorService;

    @GetMapping
    private Page<PostAuthor> getAllAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {
        return authorService.getAllAuthors(page, size, sortBy);
    }

    @GetMapping("/{id}")
    private PostAuthor findAuthorById(@PathVariable UUID id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    private PostAuthor saveAuthor(@RequestBody @Validated AuthorDTO author, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return authorService.saveAuthor(author);
    }


    @PutMapping("/{id}")
    private PostAuthor findAuthorByIdAndUpdate(@PathVariable UUID id, @RequestBody PostAuthor updatedAuthor) {
        return authorService.findAuthorByIdAndUpdate(id, updatedAuthor);
    }

    @DeleteMapping("/{id}")
    private void deleteAuthorById(@PathVariable UUID id) {
        authorService.deleteAuthorById(id);
    }
}
