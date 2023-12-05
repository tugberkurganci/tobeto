package com.tobeto.a.spring.intro.services.models.responses;

import lombok.Data;

@Data
public class UserResponse {
    private String name;
    private String email;
    private String phone;
    private String address;

}