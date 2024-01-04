package com.tobeto.pair3.services.dtos.requests;

import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CarModel {




        private int id;
        private String brand;
        private String model;
        private BigDecimal price;

        public CarModelResponse getResponse (CarModel c) {
            CarModelResponse carModelResponse=new CarModelResponse();
            carModelResponse.setId(c.getId());
            carModelResponse.setModel(c.getModel());
            carModelResponse.setBrand(c.getBrand());
            carModelResponse.setPrice(c.getPrice());
          return carModelResponse;
        }
    }

