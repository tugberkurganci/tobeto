package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

}
