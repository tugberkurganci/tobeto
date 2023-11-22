package com.tobeto.a.spring.intro.services.models.responses;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentResponse {

    private BigDecimal paymentAmount;
    private Date paymentDate;
    private String paymentOption;
    private int rentalId;

}
