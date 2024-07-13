package dev.moamenhady.greatblogsapi.service;

import dev.moamenhady.greatblogsapi.controller.AuthorController;
import dev.moamenhady.greatblogsapi.repository.AuthorRepository;
import dev.moamenhady.greatblogsapi.model.Author;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Author> getAuthorByUsername(String username) {
        return authorRepository.findAuthorByUsername(username);
    }

    public void signup(AuthorController.SignupRequest request) {

        validateUsername(request.username());
        validatePassword(request.password());
        validateEmail(request.email());

        Author author = new Author();
        author.setUsername(request.username());
        author.setPassword(passwordEncoder.encode(request.password()));
        author.setEmail(request.email());

        authorRepository.save(author);

    }

    private void validateUsername(String username) {
        if (authorRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username can't be blank");
        }
        if (!username.matches("^[a-zA-Z0-9_-]{5,20}$")) {
            throw new IllegalArgumentException("Username is not valid");
        }
    }

    private void validatePassword(String password) {
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password can't be blank");
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&^#()\\[\\]{}<>/|+=_~`.,:;\"'\\\\-])" +
                "[A-Za-z\\d@$!%*?&^#()\\[\\]{}<>/|+=_~`.,:;\"'\\\\-]{8,}$")) {
            throw new IllegalArgumentException("Password is not valid");
        }
    }

    private void validateEmail(String email) {
        if (authorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email can't be blank");
        }
        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}")) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }


    public Author updateAuthor(Long id, AuthorController.UpdateAuthorRequest request) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            author.get().setFullName(request.fullName());
            author.get().setAbout(request.about());
            return authorRepository.save(author.get());
        } else {
            throw new IllegalArgumentException("Author with id " + id + " not found");
        }
    }


}
