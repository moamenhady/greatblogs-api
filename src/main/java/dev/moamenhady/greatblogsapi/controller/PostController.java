package dev.moamenhady.greatblogsapi.controller;

import dev.moamenhady.greatblogsapi.model.Author;
import dev.moamenhady.greatblogsapi.model.Post;
import dev.moamenhady.greatblogsapi.service.PostService;
import dev.moamenhady.greatblogsapi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest request, Authentication authentication) {
        Author author = authorService.getAuthorByUsername(authentication.getName());
        try {
            Post createdPost = postService.createPost(request, author);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public record CreatePostRequest(String title, String content) {
    }

    @GetMapping("/all")
    public List<PostResponse> getAllPosts() {
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : postService.getAllPosts()) {
            responses.add(new PostResponse(post.getAuthor().getUsername(), post.getTitle(), post.getContent()));
        }
        return responses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            PostResponse postResponse = new PostResponse(post.getAuthor().getUsername(), post.getTitle(), post.getContent());
            return new ResponseEntity<>(postResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/author/{authorUsername}")
    public List<PostResponse> getPostsByAuthor(@PathVariable String authorUsername) {
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : postService.getAllPosts()) {
            if (post.getAuthor().getUsername().equals(authorUsername)) {
                responses.add(new PostResponse(authorUsername, post.getTitle(), post.getContent()));
            }
        }
        return responses;
    }

    public record PostResponse(String username, String title, String content) {
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest request,
                                           Authentication authentication) {
        Author loggedinAuthor = authorService.getAuthorByUsername(authentication.getName());
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (post.getAuthor().equals(loggedinAuthor)) {
            post = postService.updatePost(id, request);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public record UpdatePostRequest(String title, String content) {
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication authentication) {
        Author loggedinAuthor = authorService.getAuthorByUsername(authentication.getName());
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (post.getAuthor().equals(loggedinAuthor)) {
            postService.deletePost(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
