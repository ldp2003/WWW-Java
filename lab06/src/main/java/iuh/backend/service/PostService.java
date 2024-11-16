package iuh.backend.service;

import iuh.backend.models.Post;
import iuh.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Post> findAllPaging(int pageNo, int pageSize, String id, String asc) {
        Sort sort = Sort.by(Sort.Direction.fromString(asc), id);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository.findAll(pageable);
    }
}
