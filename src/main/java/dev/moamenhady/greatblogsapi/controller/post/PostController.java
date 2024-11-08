package dev.moamenhady.greatblogsapi.controller.post;

import dev.moamenhady.greatblogsapi.dto.post.CreatePostDTO;
import dev.moamenhady.greatblogsapi.dto.post.GetPostDTO;
import dev.moamenhady.greatblogsapi.dto.post.UpdatePostDTO;
import dev.moamenhady.greatblogsapi.model.person.Author;
import dev.moamenhady.greatblogsapi.model.post.Post;
import dev.moamenhady.greatblogsapi.service.post.PostService;
import dev.moamenhady.greatblogsapi.service.person.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO request, Authentication authentication) {
        Optional<Author> author = authorService.getAuthorByUsername(authentication.getName());
        try {
            Post createdPost = postService.createPost(request, author.orElseThrow());
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<GetPostDTO> getAllPosts() {
        List<GetPostDTO> responses = new ArrayList<>();
        for (Post post : postService.getAllPosts()) {
            responses.add(new GetPostDTO(post.getAuthor().getUsername(), post.getTitle(), post.getContent()));
        }
        return responses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            GetPostDTO getPostDTO = new GetPostDTO(post.getAuthor().getUsername(), post.getTitle(), post.getContent());
            return new ResponseEntity<>(getPostDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/author/{authorUsername}")
    public List<GetPostDTO> getPostsByAuthor(@PathVariable String authorUsername) {
        List<GetPostDTO> responses = new ArrayList<>();
        for (Post post : postService.getAllPosts()) {
            if (post.getAuthor().getUsername().equals(authorUsername)) {
                responses.add(new GetPostDTO(authorUsername, post.getTitle(), post.getContent()));
            }
        }
        return responses;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody UpdatePostDTO request,
                                           Authentication authentication) {
        Optional<Author> loggedInAuthor = authorService.getAuthorByUsername(authentication.getName());
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (loggedInAuthor.isPresent() && post.getAuthor().equals(loggedInAuthor.get())) {
            post = postService.updatePost(id, request);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication authentication) {
        Optional<Author> loggedInAuthor = authorService.getAuthorByUsername(authentication.getName());
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (loggedInAuthor.isPresent() && post.getAuthor().equals(loggedInAuthor.get())) {
            postService.deletePost(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
