package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateRentalRequest {
    private int id;
    private Date rentalDate;
    private Date returnDate;


}

