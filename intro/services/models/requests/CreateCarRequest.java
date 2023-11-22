package com.tobeto.a.spring.intro.services.models.requests;
import lombok.Data;

@Data
public class CreateCarRequest {
    private int modelYear;
    private String modelName;
    private double dailyPrice;
    private String color;
    private String status;
    private int brandId;
    private int carSupplierId;
}

