package dev.moamenhady.greatblogsapi.controller;

import dev.moamenhady.greatblogsapi.service.AuthorService;
import dev.moamenhady.greatblogsapi.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        try {
            authorService.signup(request);
            return new ResponseEntity<>("Author successfully created", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("already exists")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    public record SignupRequest(String username, String password, String email) {
    }


    @GetMapping("/all")
    public List<String> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(Author::getUsername)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            String username = author.get().getUsername();
            return new ResponseEntity<>(username, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody UpdateAuthorRequest request,
                                               Authentication authentication) {
        Optional<Author> loggedInAuthor = authorService.getAuthorByUsername(authentication.getName());
        if (loggedInAuthor.isPresent() && Objects.equals(id, loggedInAuthor.get().getId())) {
            Author author = authorService.updateAuthor(id, request);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public record UpdateAuthorRequest(String fullName, String about) {
    }

}
