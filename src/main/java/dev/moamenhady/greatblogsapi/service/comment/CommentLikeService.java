package dev.moamenhady.greatblogsapi.service.comment;

import dev.moamenhady.greatblogsapi.repository.comment.CommentLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeService(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }
}
