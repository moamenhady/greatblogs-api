package dev.moamenhady.greatblogsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.*;

@Data
@Entity
public class Author implements UserDetails {

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{5,20}$";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private Set<Post> posts;

    private String authority = ROLE_USER;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }


}
