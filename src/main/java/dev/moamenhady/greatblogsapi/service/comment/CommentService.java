package dev.moamenhady.greatblogsapi.service.comment;

import dev.moamenhady.greatblogsapi.repository.comment.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
