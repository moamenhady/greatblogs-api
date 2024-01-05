package dev.moamenhady.greatblogsapi.security;

import dev.moamenhady.greatblogsapi.repository.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorDetailsService implements UserDetailsService {

    private final AuthorRepository authorRepository;

    public AuthorDetailsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authorRepository
                .findAuthorByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Author not found"));
    }
}
