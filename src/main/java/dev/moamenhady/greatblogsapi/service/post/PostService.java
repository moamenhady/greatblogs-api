package dev.moamenhady.greatblogsapi.service.post;

import dev.moamenhady.greatblogsapi.dto.post.CreatePostDTO;
import dev.moamenhady.greatblogsapi.dto.post.UpdatePostDTO;
import dev.moamenhady.greatblogsapi.model.person.Author;
import dev.moamenhady.greatblogsapi.model.post.Post;
import dev.moamenhady.greatblogsapi.repository.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(CreatePostDTO request, Author author) {

        Post post = new Post();
        post.setAuthor(author);
        post.setTitle(request.title());
        post.setContent(request.content());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }


    public Post updatePost(Long id, UpdatePostDTO request) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findPostById(id);
            post.setTitle(request.title());
            post.setContent(request.content());
            return postRepository.save(post);
        } else {
            throw new IllegalArgumentException("Post with id " + id + " not found");
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
