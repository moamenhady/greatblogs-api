package dev.moamenhady.greatblogsapi.service.post;

import dev.moamenhady.greatblogsapi.repository.post.PostLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }
}
