package com.tobeto.a.spring.intro.services.models.requests;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvaibleCarsRequest {

    @NotNull(message = "Rental date cannot be null")
    @FutureOrPresent(message = "Rental date should be in the present or future")
    private LocalDate rentalDate;

    @Future(message = "Return date should be in the future")
    private LocalDate returnDate;

    @NotBlank(message = "Start car supplier cannot be blank")
    private String startCarSupplier;

    @NotBlank(message = "Finish car supplier cannot be blank")
    private String finishCarSupplier;
}
