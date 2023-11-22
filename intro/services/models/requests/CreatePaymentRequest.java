package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreatePaymentRequest {
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private String paymentOption;



}
