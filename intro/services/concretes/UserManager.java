package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.User;
import com.tobeto.a.spring.intro.services.models.requests.CreateUserRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateUserRequest;
import com.tobeto.a.spring.intro.services.models.responses.UserResponse;
import com.tobeto.a.spring.intro.repositories.UserRepository;
import com.tobeto.a.spring.intro.services.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;


    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToResponse(user);
    }

    @Override
    public void createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());

        userRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updatedUserRequest) {
        Optional<User> existingUser = userRepository.findById(updatedUserRequest.getId());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUserRequest.getName());
            user.setEmail(updatedUserRequest.getEmail());
            user.setPhone(updatedUserRequest.getPhone());
            user.setAddress(updatedUserRequest.getAddress());

            userRepository.save(user);
        } else {
            throw new RuntimeException("There is no user");
        }
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public User getOriginalUserById(int userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    private UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setAddress(user.getAddress());

        return userResponse;
    }
}
