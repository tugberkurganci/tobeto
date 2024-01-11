package com.tobeto.pair3.services.dtos.requests;

import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import lombok.Data;

import java.util.List;

@Data
public class FilterCarRequest {

    private String brand;
    private String model;
    private int minPrice;
    private int maxPrice;
    private List<CarModel> carList;
}
