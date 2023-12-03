package com.tobeto.a.spring.intro.services.models.responses;

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

    public CarResponse(int modelYear,String modelName,double dailyPrice,String brandName,
                    String carSupplierName
   ) {
       this.modelYear=modelYear;
       this.modelName=modelName;
       this.dailyPrice=dailyPrice;
       this.brandName=brandName;
       this.carSupplierName=carSupplierName;

   }
}

