package com.tobeto.a.spring.intro.services.models.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    @NotNull(message = "Payment amount cannot be null")
    @DecimalMin(value = "0.01", message = "Payment amount should be greater than or equal to 0.01")
    private BigDecimal paymentAmount;

    @NotNull(message = "Payment date cannot be null")
    @FutureOrPresent(message = "Payment date should be in the present or future")
    private Date paymentDate;

    @NotBlank(message = "Payment option cannot be blank")
    private String paymentOption;

    @Positive(message = "Rental ID should be a positive value")
    private int rentalId;

}
