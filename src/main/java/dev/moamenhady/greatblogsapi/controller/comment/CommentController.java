package dev.moamenhady.greatblogsapi.controller.comment;

import dev.moamenhady.greatblogsapi.service.comment.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/comments/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
