package dev.moamenhady.greatblogsapi.repository;

import dev.moamenhady.greatblogsapi.model.Author;
import dev.moamenhady.greatblogsapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Author author);

    Post getPostById(Long id);
}
