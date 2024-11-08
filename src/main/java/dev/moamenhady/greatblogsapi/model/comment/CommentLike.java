package dev.moamenhady.greatblogsapi.model.comment;

import dev.moamenhady.greatblogsapi.model.person.Author;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Author.class)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Comment.class)
    private Comment comment;

}
