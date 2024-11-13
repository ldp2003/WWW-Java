package backend.service;

import backend.models.PostComment;
import backend.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public void addPostComment(PostComment postComment) {
        postCommentRepository.save(postComment);
    }

    public void updatePostComment(PostComment postComment) {
        postCommentRepository.save(postComment);
    }

    public void deletePostComment(PostComment postComment) {
        postCommentRepository.delete(postComment);
    }

    public PostComment getPostCommentById(Long id) {
        return postCommentRepository.findById(id).orElse(null);
    }

    public Iterable<PostComment> getAllPostComments() {
        return postCommentRepository.findAll();
    }
}
