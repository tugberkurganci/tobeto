package com.tobeto.pair3.services.abstracts;

import com.tobeto.pair3.entities.User;
import com.tobeto.pair3.services.dtos.requests.CreateUserRequest;
import com.tobeto.pair3.services.dtos.requests.PasswordResetRequest;
import com.tobeto.pair3.services.dtos.requests.UpdateUserRequest;
import com.tobeto.pair3.services.dtos.responses.GetAllUsersResponse;
import com.tobeto.pair3.services.dtos.responses.GetUserResponse;

import java.util.List;

public interface UserService {
    void add(CreateUserRequest createUserRequest);

    void update(UpdateUserRequest updateUserRequest);

    void delete(Integer id);

    List<GetAllUsersResponse> getAll();

    GetUserResponse getById(int id);

    boolean existsById(int userId);


    User getOriginalUserById(int userId);

    User findByEmail(String email);

    void createUserV2(CreateUserRequest userRequest);


}
