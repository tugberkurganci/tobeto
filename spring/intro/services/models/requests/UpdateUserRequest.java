package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UpdateUserRequest {
    @Positive(message = "ID should be a positive value")
    @NotBlank(message = "ID must have")
    private int id;


    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;


    private String phone;


    private String address;
}
