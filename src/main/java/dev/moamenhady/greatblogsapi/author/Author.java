package dev.moamenhady.greatblogsapi.author;

import dev.moamenhady.greatblogsapi.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "username can't be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{5,20}$", message = "username is not valid")
    private String username;

    @NotBlank(message = "password can't be blank")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&^#()\\[\\]{}<>/|+=_~`.,:;\"'\\\\-])" +
            "[A-Za-z\\d@$!%*?&^#()\\[\\]{}<>/|+=_~`.,:;\"'\\\\-]{8,}$",
            message = "password is not valid")
    private String password;

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is not valid")
    private String email;

    private String fullName;

    @Lob
    private String about;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    public Author() {
    }

    public Author(Long id, String username, String password, String email, String fullName, String about, Set<Post> posts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.about = about;
        this.posts = posts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

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

    public String getPassword() {
        return password;
    }

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
        return Objects.equals(posts, author.posts);
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
        return result;
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
                '}';
    }
}
