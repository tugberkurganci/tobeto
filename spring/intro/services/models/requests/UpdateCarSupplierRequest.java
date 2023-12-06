package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UpdateCarSupplierRequest {
    @NotBlank(message = "ID must have")
    private int id;
    private String name;
    private String location;

}
