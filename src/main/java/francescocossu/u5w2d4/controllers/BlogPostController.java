package francescocossu.u5w2d4.controllers;

import francescocossu.u5w2d4.entities.BlogPost;
import francescocossu.u5w2d4.exceptions.BadRequestException;
import francescocossu.u5w2d4.payloads.BlogPostDTO;
import francescocossu.u5w2d4.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;


    @GetMapping
    private List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    private BlogPost findBlogPostById(@PathVariable UUID id) {
        return blogPostService.findBlogPostById(id);
    }

    @PostMapping
    private BlogPost saveBlogPost(@RequestBody @Validated BlogPostDTO blogPost, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return blogPostService.saveBlogPost(blogPost);
    }


    @PutMapping("/{id}")
    private BlogPost findBlogPostByIdAndUpdate(@PathVariable UUID id, @RequestBody BlogPost blogPostUpdated) {
        return blogPostService.findBlogPostByIdAndUpdate(id, blogPostUpdated);
    }

    @DeleteMapping("/{id}")
    private void deleteBlogPostById(@PathVariable UUID id) {
        blogPostService.deleteBlogPostById(id);
    }


    @PostMapping("/{id}/cover")
    public String uploadCover(@RequestParam("cover") MultipartFile file, @PathVariable UUID id) throws IOException {
        return blogPostService.uploadCover(file, id);
    }
}