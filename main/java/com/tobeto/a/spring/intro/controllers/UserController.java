package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.User;
import com.tobeto.a.spring.intro.repositories.UserRepository;
import com.tobeto.a.spring.intro.services.abstracts.UserService;
import com.tobeto.a.spring.intro.services.models.requests.CreateUserRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateUserRequest;
import com.tobeto.a.spring.intro.services.models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody List<CreateUserRequest> userRequest) {
        userService.createUser(userRequest);
    }

    @PutMapping
    public void updateUser(@RequestBody UpdateUserRequest updatedUserRequest) {
        userService.updateUser(updatedUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/info")
    public List<User> getUserInfo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "address", required = false) String address) {



        List<User> users=userService.getUserWithFilter(name, email, phone, address);

        return users;
    }
    @GetMapping("/search/{input}")
    public List<User> searchUsers(@PathVariable String input) {
        return userService.findLikeNameByInput(input);
    }
}

