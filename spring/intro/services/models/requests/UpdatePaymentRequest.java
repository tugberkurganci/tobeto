package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class UpdatePaymentRequest {
    @NotBlank(message = "ID must have")
    private int id;
    @Positive(message = "Payment amount should be a positive value")
    private BigDecimal paymentAmount;

    private Date paymentDate;

    private String paymentOption;

    @Positive(message = "Rental ID should be a positive value")
    private int rentalId;
}
