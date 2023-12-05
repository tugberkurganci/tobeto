package com.tobeto.a.spring.intro.services.models.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RentalResponse {

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private BigDecimal paymentValue;
    private String userName;
    private String carName;


}
