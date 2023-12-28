package dev.moamenhady.greatblogsapi.author;

import dev.moamenhady.greatblogsapi.post.Post;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        if (authorRepository.findById(id).isPresent()) {
            updatedAuthor.setId(id);
            Set<Post> posts = new HashSet<>(authorRepository.findById(id).get().getPosts());
            authorRepository.getReferenceById(id).getPosts().clear();
            updatedAuthor.setPosts(posts);
            return authorRepository.save(updatedAuthor);
        } else {
            throw new IllegalArgumentException("Author with id " + id + " not found");
        }
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
