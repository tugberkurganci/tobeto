package com.tobeto.pair3.services.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {


    @NotNull
    private LocalDate pickupDate;

    @NotNull
    private LocalDate dropoffDate;

    @NotBlank
    private String pickupLocation;


    private String dropoffLocation;

    private int carId;

    private int modelId;


    private int userId;
}
