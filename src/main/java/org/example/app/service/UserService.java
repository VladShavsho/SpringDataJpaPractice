package org.example.app.service;

import org.example.app.entity.Post;
import org.example.app.entity.User;
import org.example.app.repository.PostRepository;
import org.example.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public User createUserWithPosts(User user, List<Post> posts) {
        for (Post post : posts) {
            post.setUser(user);
        }
        user.setPosts(posts);
        return userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Transactional
    public void createUserWithError(User user) {
        userRepository.save(user);
        throw new RuntimeException("Rollback occurred");
    }
}
