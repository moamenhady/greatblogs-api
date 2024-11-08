package dev.moamenhady.greatblogsapi.model.post;

import dev.moamenhady.greatblogsapi.model.person.Author;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Post.class)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Author.class)
    private Author author;

}
