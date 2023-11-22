package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.User;
import com.tobeto.a.spring.intro.services.models.requests.CreateUserRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateUserRequest;
import com.tobeto.a.spring.intro.services.models.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(int id);
    void createUser(CreateUserRequest userRequest);
    void updateUser(UpdateUserRequest updatedUserRequest);
    void deleteUser(int id);

    User getOriginalUserById(int userId);
}