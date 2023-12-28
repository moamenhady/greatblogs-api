package dev.moamenhady.greatblogsapi.post;

import dev.moamenhady.greatblogsapi.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Author author);
}
