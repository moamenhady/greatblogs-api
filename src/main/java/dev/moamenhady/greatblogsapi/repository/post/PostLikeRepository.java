package dev.moamenhady.greatblogsapi.repository.post;

import dev.moamenhady.greatblogsapi.model.post.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository  extends JpaRepository<PostLike, Long> {
}
