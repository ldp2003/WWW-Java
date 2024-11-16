package iuh.frontend;

import iuh.backend.models.Post;
import iuh.backend.models.PostComment;
import iuh.backend.models.User;
import iuh.backend.service.PostCommentService;
import iuh.backend.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping("/post-comment")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public String addPostComment(@RequestParam("postId") Long postId,
                                 @RequestParam("content") String content,
                                 @RequestParam("parentCommentId") Optional<Long> parentCommentId,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "index";
        }

        Post post = postService.getPostById(postId);
        if (post == null) {
            return "redirect:/post/posts";
        }

        PostComment newComment = new PostComment();
        newComment.setContent(content);
        newComment.setPost(post);
        if(parentCommentId.isPresent()) {
            Long parentCommentIdLong = parentCommentId.get();
            newComment.setParent(postCommentService.getPostCommentById(parentCommentIdLong));
        }
        newComment.setTitle("huh");
        newComment.setCreatedAt(Instant.now());
        newComment.setPublishedAt(Instant.now());
        System.out.println("content: " + content);
        System.out.println("postId: " + postId);
        System.out.println("parentCommentId: " + parentCommentId);
        postCommentService.addPostComment(newComment);

        return "redirect:/post/view/" + postId;
    }
}
