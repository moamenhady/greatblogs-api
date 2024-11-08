package dev.moamenhady.greatblogsapi.model.post;

import dev.moamenhady.greatblogsapi.model.comment.Comment;
import dev.moamenhady.greatblogsapi.model.person.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotBlank(message = "title can't be blank")
    @Size(max = 255, message = "title can't exceed 255 characters")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "content can't be blank")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Author.class)
    private Author author;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post",
            targetEntity = Comment.class)
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post",
            targetEntity = PostLike.class)
    private Set<PostLike> likes;

    private boolean restricted = false;

}
