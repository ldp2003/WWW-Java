package iuh.frontend;

import iuh.backend.models.Post;
import iuh.backend.models.User;
import iuh.backend.service.PostService;
import iuh.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public String showPostsPaging(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page< Post> postPage = postService.findAllPaging(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("posts", postPage);
        int totalPages = postPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "posts/posts";
    }

    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/postDetail";
    }

    @GetMapping("/add")
    public String showAddPostForm(Model model, HttpSession session) {
        model.addAttribute("post", new Post());
        if(session.getAttribute("user") == null)
            return "index";
        return "posts/addPost";
    }

    @PostMapping("/add")
    public String addPost(Post post, HttpSession session) {
        post.setPublishedAt(Instant.now());
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());
        post.setPublished(true);
        post.setAuthor((User) session.getAttribute("user"));
        postService.addPost(post);
        return "redirect:/post/posts";
    }
}
