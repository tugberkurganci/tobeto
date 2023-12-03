package com.tobeto.a.spring.intro.services.models.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvaibleCarsRequest {

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String startCarSupplier;
    private String finishCarSupplier;
}
