package com.tobeto.a.spring.intro.services.models.responses;

import com.tobeto.a.spring.intro.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private int modelYear;
    private String modelName;
    private double dailyPrice;
    private String color;
    private String status;
    private String brandName;
    private String carSupplierName;
    private int carId;

    public CarResponse(int modelYear,String modelName,double dailyPrice,String brandName,
                    String carSupplierName
   ) {
       this.modelYear=modelYear;
       this.modelName=modelName;
       this.dailyPrice=dailyPrice;
       this.brandName=brandName;
       this.carSupplierName=carSupplierName;

   }
    public CarResponse(Car car
    ) {
        this.modelYear=car.getModelYear();
        this.modelName=car.getModelName();
        this.dailyPrice=car.getDailyPrice();
        this.brandName=car.getModelName();
        this.carSupplierName=car.getCarSupplier().getName();

    }
}

