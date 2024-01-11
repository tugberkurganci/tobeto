package com.tobeto.pair3.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record RentalResponse(
        int id,
        String pickupLocation,
        String dropoffLocation,
        LocalDate pickupDate,
        LocalDate dropoffDate,
        BigDecimal totalPrice) {
}
