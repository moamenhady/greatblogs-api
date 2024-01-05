package dev.moamenhady.greatblogsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;


@Entity
public class Author implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "username can't be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{5,20}$", message = "username is not valid")
    private String username;

    @NotBlank(message = "password can't be blank")
    private String password;

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is not valid")
    private String email;

    private String fullName;

    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = EAGER)
    private Set<Post> posts;

    private String authority = "ROLE_USER";

    public Author() {
    }

    public Author(Long id, String username, String password, String email, String fullName,
                  String about, Set<Post> posts, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.about = about;
        this.posts = posts;
        this.authority = authority;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String role) {
        this.authority = role;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", about='" + about + '\'' +
                ", posts=" + posts +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!Objects.equals(id, author.id)) return false;
        if (!Objects.equals(username, author.username)) return false;
        if (!Objects.equals(password, author.password)) return false;
        if (!Objects.equals(email, author.email)) return false;
        if (!Objects.equals(fullName, author.fullName)) return false;
        if (!Objects.equals(about, author.about)) return false;
        if (!Objects.equals(posts, author.posts)) return false;
        return Objects.equals(authority, author.authority);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

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
}
