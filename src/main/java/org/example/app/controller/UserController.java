package org.example.app.controller;

import org.example.app.entity.Post;
import org.example.app.entity.User;
import org.example.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUserWithPosts(user, user.getPosts()));
    }

    @GetMapping("/by-name")
    public List<User> getByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/by-domain")
    public List<User> getByEmailDomain(@RequestParam String domain) {
        return userService.getUsersByEmailDomain(domain);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return userService.getPostsByUserId(userId);
    }

    @PostMapping("/test-error")
    public void testRollback(@RequestBody User user) {
        userService.createUserWithError(user);
    }
}

