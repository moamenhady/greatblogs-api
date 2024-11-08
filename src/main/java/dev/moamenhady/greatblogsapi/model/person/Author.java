package dev.moamenhady.greatblogsapi.model.person;

import dev.moamenhady.greatblogsapi.model.comment.Comment;
import dev.moamenhady.greatblogsapi.model.post.Post;
import dev.moamenhady.greatblogsapi.model.comment.CommentLike;
import dev.moamenhady.greatblogsapi.model.post.PostLike;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author implements UserDetails {

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{5,20}$";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(unique = true)
    @NotBlank(message = "username can't be blank")
    @Pattern(regexp = USERNAME_PATTERN, message = "username is not valid")
    private String username;

    @NotBlank(message = "password can't be blank")
    private String password;

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is not valid")
    private String email;

    private String fullName;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Lob
    @Basic(fetch = EAGER)
    @Column(columnDefinition = "bytea")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "author_follow",
        joinColumns = @JoinColumn(name = "follower"),
        inverseJoinColumns = @JoinColumn(name = "followed")
    )
    private Set<Author> following;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "following", targetEntity = Author.class)
    private Set<Author> followers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author",
            targetEntity = Post.class)
    private Set<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author",
            targetEntity = PostLike.class)
    private Set<PostLike> postLikes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author",
            targetEntity = Comment.class)
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author",
            targetEntity = CommentLike.class)
    private Set<CommentLike> commentLikes;

    private String authority = ROLE_USER;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }


}
