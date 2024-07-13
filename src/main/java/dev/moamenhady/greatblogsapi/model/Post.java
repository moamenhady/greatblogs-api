package dev.moamenhady.greatblogsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @NotBlank(message = "title can't be blank")
    @Size(max = 255, message = "title can't exceed 255 characters")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "content can't be blank")
    private String content;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    private int viewCount = 0;

}
