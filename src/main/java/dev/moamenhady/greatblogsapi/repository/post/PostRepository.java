package dev.moamenhady.greatblogsapi.repository.post;

import dev.moamenhady.greatblogsapi.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
}
