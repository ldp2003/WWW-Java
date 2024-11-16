package iuh.frontend;

import iuh.backend.models.User;
import iuh.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("loginParam") String loginParam, @RequestParam("password") String password, HttpSession session, Model model) {
        User user = userService.findByMobileOrEmail(loginParam);
        if (user != null) {
            if (user.getPasswordHash().equals(hashPasswordMD5(password))) {
                user.setLastLogin(Instant.now());
                userService.updateUser(user);
                session.setAttribute("user", user);
                return "redirect:/post/posts";
            } else {
                model.addAttribute("error", "Invalid password");
            }
        } else {
            model.addAttribute("error", "User not found");
        }
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute User user) {
        user.setPasswordHash(hashPasswordMD5(user.getPasswordHash()));
        user.setRegisteredAt(Instant.now());
        user.setLastLogin(Instant.now());
        user.setProfile("");
        user.setIntro("");
        System.out.println(user.getPasswordHash().length());
        System.out.println(user.toString());
        userService.addUser(user);
        return new RedirectView("/post/posts");
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "index";
        }
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/editUser";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User user, HttpSession session) {
        User oldUser = userService.getUserById(id);
        user.setRegisteredAt(oldUser.getRegisteredAt());
        user.setLastLogin(oldUser.getLastLogin());
        if(user.getPasswordHash().trim().isEmpty())
            user.setPasswordHash(oldUser.getPasswordHash());
        else
            user.setPasswordHash(hashPasswordMD5(user.getPasswordHash()));
        userService.updateUser(user);
        session.setAttribute("user", user);
        return "redirect:/user/profile?id=" + id;
    }
}