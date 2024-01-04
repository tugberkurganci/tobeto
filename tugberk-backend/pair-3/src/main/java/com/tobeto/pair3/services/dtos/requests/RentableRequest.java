package com.tobeto.pair3.services.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class RentableRequest {

    @NotNull
    @Future
    private LocalDate pickupDate;

    @NotNull
    @Future
    private LocalDate dropoffDate;

    @NotBlank
    private String pickupLocation;


    private String dropoffLocation;
}