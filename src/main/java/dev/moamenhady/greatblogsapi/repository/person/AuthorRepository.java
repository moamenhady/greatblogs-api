package dev.moamenhady.greatblogsapi.repository.person;

import dev.moamenhady.greatblogsapi.model.person.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
