package iuh.backend.repositories;

import iuh.backend.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @Query("SELECT pc FROM PostComment pc WHERE pc.post.id = :postId")
    Iterable<PostComment> findByPostId(Long postId);

    @Query("SELECT pc FROM PostComment pc WHERE pc.parent.id = :parentId")
    Iterable<PostComment> findByParentId(Long parentId);
}