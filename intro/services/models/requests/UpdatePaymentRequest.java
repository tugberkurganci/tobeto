package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdatePaymentRequest {
    private int id;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private String paymentOption;
    private int rentalId;

}

