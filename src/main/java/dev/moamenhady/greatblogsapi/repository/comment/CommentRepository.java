package dev.moamenhady.greatblogsapi.repository.comment;

import dev.moamenhady.greatblogsapi.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
