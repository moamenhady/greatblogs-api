package dev.moamenhady.greatblogsapi.service;

import dev.moamenhady.greatblogsapi.controller.PostController;
import dev.moamenhady.greatblogsapi.model.Author;
import dev.moamenhady.greatblogsapi.model.Post;
import dev.moamenhady.greatblogsapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostController.CreatePostRequest request, Author author) {

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


    public Post updatePost(Long id, PostController.UpdatePostRequest request) {
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
