package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateRentalRequest {
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String startCarSupplier;
    private String finishCarSupplier;
    private int userId;
    private int carId;
}
