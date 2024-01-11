package com.tobeto.pair3.services.dtos.requests;

import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SortCarsRequest {

    private String sortType;
    private List<CarModel> carList;

}
