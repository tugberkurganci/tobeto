package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;


    @Pattern(regexp = "\\d{10}", message = "Phone should be a 10-digit number")
    private String phone;


    private String address;

}
