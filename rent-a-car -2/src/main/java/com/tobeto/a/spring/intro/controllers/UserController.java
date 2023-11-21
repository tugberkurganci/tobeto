package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.User;
import com.tobeto.a.spring.intro.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping
    public User updateUser( @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(updatedUser.getId());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // userDetails ve rentals ilişkilerini güncelleme (eğer gerekirse)
            user.setUserDetails(updatedUser.getUserDetails());
            user.setRentals(updatedUser.getRentals());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + updatedUser.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
