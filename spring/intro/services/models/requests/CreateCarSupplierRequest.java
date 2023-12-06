package com.tobeto.a.spring.intro.services.models.requests;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCarSupplierRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;


}
