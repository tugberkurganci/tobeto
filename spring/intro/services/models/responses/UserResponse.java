package com.tobeto.a.spring.intro.services.models.responses;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class UserResponse {
    private String name;
    private String email;
    private String phone;
    private String address;

}