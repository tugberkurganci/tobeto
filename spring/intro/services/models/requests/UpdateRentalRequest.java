package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import java.util.Date;

@Data
public class UpdateRentalRequest {
    @NotBlank(message = "ID must have")
    private int id;
    private LocalDate rentalDate;
    private LocalDate returnDate;


}

