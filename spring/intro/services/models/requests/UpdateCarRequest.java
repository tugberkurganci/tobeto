package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UpdateCarRequest {
    @NotBlank(message = "ID must have")
    private int id;
    @Positive(message = "Model year should be a positive value")
    private int modelYear;

    private String modelName;

    @PositiveOrZero(message = "Daily price should be a positive or zero value")
    private double dailyPrice;

    private String color;

    private String status;

    @Positive(message = "Car supplier ID should be a positive value")
    private int carSupplierId;

}
