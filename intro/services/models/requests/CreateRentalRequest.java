package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.util.Date;

@Data
public class CreateRentalRequest {
    private Date rentalDate;
    private Date returnDate;
    private CreatePaymentRequest createPaymentRequest;
    private int userId;
    private int carId;
}
