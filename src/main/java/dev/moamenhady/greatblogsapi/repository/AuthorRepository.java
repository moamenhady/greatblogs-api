package dev.moamenhady.greatblogsapi.repository;

import dev.moamenhady.greatblogsapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByUsername(String username);

    Author findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
