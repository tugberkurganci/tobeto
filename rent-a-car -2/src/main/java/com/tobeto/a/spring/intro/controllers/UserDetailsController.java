package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.UserDetails;
import com.tobeto.a.spring.intro.repositories.UserDetailsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userDetails")
public class UserDetailsController {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsController(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping
    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserDetails getUserDetailsById(@PathVariable int id) {
        return userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserDetails not found with id: " + id));
    }

    @PostMapping
    public UserDetails createUserDetails(@RequestBody UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    @PutMapping
    public UserDetails updateUserDetails(@RequestBody UserDetails updatedUserDetails) {
        Optional<UserDetails> existingUserDetails = userDetailsRepository.findById(updatedUserDetails.getId());

        if (existingUserDetails.isPresent()) {
            UserDetails userDetails = existingUserDetails.get();
            userDetails.setName(updatedUserDetails.getName());
            userDetails.setEmail(updatedUserDetails.getEmail());
            userDetails.setPhone(updatedUserDetails.getPhone());
            userDetails.setAddress(updatedUserDetails.getAddress());
            // user ilişkisini güncelleme (eğer gerekirse)
            userDetails.setUser(updatedUserDetails.getUser());

            return userDetailsRepository.save(userDetails);
        } else {
            throw new RuntimeException("UserDetails not found with id: " + updatedUserDetails.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserDetails(@PathVariable int id) {
        if (userDetailsRepository.existsById(id)) {
            userDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("UserDetails not found with id: " + id);
        }
    }
}
