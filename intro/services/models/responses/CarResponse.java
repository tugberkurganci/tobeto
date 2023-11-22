package com.tobeto.a.spring.intro.services.models.responses;

import lombok.Data;

@Data
public class CarResponse {

    private int modelYear;
    private String modelName;
    private double dailyPrice;
    private String color;
    private String status;
    private String brandName;
    private String carSupplierName;
}

