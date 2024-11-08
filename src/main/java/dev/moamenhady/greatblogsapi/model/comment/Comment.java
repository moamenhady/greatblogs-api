package dev.moamenhady.greatblogsapi.model.comment;

import dev.moamenhady.greatblogsapi.model.post.Post;
import dev.moamenhady.greatblogsapi.model.person.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Author.class)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Post.class)
    private Post post;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "comment",
            targetEntity = CommentLike.class)
    private Set<CommentLike> likes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parent")
    private Set<Comment> replies;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Comment.class)
    private Comment parent;

}
