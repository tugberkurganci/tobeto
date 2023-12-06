package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import jakarta.validation.constraints.*;


@Data
public class CreateRentalRequest {

    @NotNull(message = "Rental date cannot be null")
    private LocalDate rentalDate;

    @NotNull(message = "Return date cannot be null")
    @Future(message = "Return date should be in the future")
    private LocalDate returnDate;

    @NotBlank(message = "Start car supplier cannot be blank")
    private String startCarSupplier;

    @NotBlank(message = "Finish car supplier cannot be blank")
    private String finishCarSupplier;

    @Positive(message = "User ID should be a positive value")
    private int userId;

    @Positive(message = "Car ID should be a positive value")
    private int carId;
}
