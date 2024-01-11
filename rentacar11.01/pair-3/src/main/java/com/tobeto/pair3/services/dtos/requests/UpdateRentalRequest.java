package com.tobeto.pair3.services.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tobeto.pair3.entities.RentalStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateRentalRequest {

    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    private int endKilometer;

    private BigDecimal totalPrice;

    private int userId;

    private int carId;

    private RentalStatus rentalStatus;


}
