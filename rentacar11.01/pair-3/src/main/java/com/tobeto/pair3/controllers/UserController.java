package com.tobeto.pair3.controllers;

import com.tobeto.pair3.services.abstracts.UserService;
import com.tobeto.pair3.services.dtos.requests.*;
import com.tobeto.pair3.services.dtos.responses.GenericMessage;
import com.tobeto.pair3.services.dtos.responses.GetAllUsersResponse;
import com.tobeto.pair3.services.dtos.responses.GetUserResponse;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void add(@RequestBody CreateUserRequest createUserRequest){
        userService.add(createUserRequest);
    }
    @PostMapping("/signup")
    public GenericMessage createUserv2(@RequestBody @Valid CreateUserRequest userRequest) {
        userService.createUserV2(userRequest);

        return new GenericMessage("user created");

    }

    @PutMapping
    @PreAuthorize("#updateUserRequest.id == principal.id")
    public void update(@RequestBody UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") int id){
        userService.delete(id);
    }
    @GetMapping
    public List<GetAllUsersResponse> getAll(){
        return userService.getAll();
    }
    @GetMapping("{id}")
    public GetUserResponse getById(@PathVariable("id") int id){
        return userService.getById(id);
    }


}


