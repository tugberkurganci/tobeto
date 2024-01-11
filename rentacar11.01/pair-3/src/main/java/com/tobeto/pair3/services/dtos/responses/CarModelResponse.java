package com.tobeto.pair3.services.dtos.responses;

import com.tobeto.pair3.entities.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CarModelResponse {

    private int id;
    private String brand;
    private String model;
    private BigDecimal price;

    public CarModelResponse(Car car) {
        this.id=car.getModel().getId();
        this.brand=car.getModel().getBrand().getName();
        this.model=car.getModel().getName();
        this.price=car.getDailyPrice();

    }
}
