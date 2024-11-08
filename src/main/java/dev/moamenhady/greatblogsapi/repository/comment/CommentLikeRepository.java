package dev.moamenhady.greatblogsapi.repository.comment;

import dev.moamenhady.greatblogsapi.model.comment.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}
