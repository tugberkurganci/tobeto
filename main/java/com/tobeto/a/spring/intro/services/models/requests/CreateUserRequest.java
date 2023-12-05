package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
    private String address;

}
