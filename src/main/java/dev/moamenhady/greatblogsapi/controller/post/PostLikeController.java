package dev.moamenhady.greatblogsapi.controller.post;

import dev.moamenhady.greatblogsapi.service.post.PostLikeService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/posts/likes/")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }
}
