package francescocossu.u5w2d4.services;

import francescocossu.u5w2d4.entities.BlogPost;
import francescocossu.u5w2d4.exceptions.NotFoundException;
import francescocossu.u5w2d4.payloads.BlogPostDTO;
import francescocossu.u5w2d4.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostService {


    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private PostAuthorService postAuthorService;

    public BlogPost saveBlogPost(BlogPostDTO blogPost) {

        BlogPost blogpost1 = new BlogPost(blogPost.category(), blogPost.title(), blogPost.body(), blogPost.readingTime(), postAuthorService.findAuthorById(blogPost.authorId()));

        blogPostRepository.save(blogpost1);
        return blogpost1;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost findBlogPostById(UUID id) {

        return blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public BlogPost findBlogPostByIdAndUpdate(UUID id, BlogPost updatedBlogPost) {

        BlogPost found = this.findBlogPostById(id);
        found.setCategory(updatedBlogPost.getCategory());
        found.setTitle(updatedBlogPost.getTitle());
        found.setBody(updatedBlogPost.getBody());
        found.setReadingTime(updatedBlogPost.getReadingTime());
        found.setCover(updatedBlogPost.getCover());
        return this.blogPostRepository.save(found);
    }

    public void deleteBlogPostById(UUID id) {

        blogPostRepository.deleteById(id);
    }


}
