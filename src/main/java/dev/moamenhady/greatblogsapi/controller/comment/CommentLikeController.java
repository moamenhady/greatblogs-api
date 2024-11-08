package dev.moamenhady.greatblogsapi.controller.comment;

import dev.moamenhady.greatblogsapi.service.comment.CommentLikeService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/comments/likes/")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }
}
