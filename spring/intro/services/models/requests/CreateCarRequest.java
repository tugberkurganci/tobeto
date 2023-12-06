package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class CreateCarRequest {

    @Min(value = 2010, message = "Model year should be at least 2010")
    private int modelYear;

    @NotBlank(message = "Model name cannot be blank")
    private String modelName;

    @Positive(message = "Daily price should be a positive value")
    private double dailyPrice;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @Positive(message = "Brand ID should be a positive value")
    private int brandId;

    @Positive(message = "Car supplier ID should be a positive value")
    private int carSupplierId;

}

