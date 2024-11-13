package backend.service;

import backend.models.Post;
import backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
