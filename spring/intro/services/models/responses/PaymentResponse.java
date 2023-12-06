package com.tobeto.a.spring.intro.services.models.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private BigDecimal paymentAmount;
    private Date paymentDate;
    private String paymentOption;
    private int rentalId;

}
