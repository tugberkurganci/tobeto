package com.tobeto.pair3.services.abstracts;

import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.services.dtos.requests.*;
import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import com.tobeto.pair3.services.dtos.responses.GetAllBrandResponse;
import com.tobeto.pair3.services.dtos.responses.GetBrandResponse;
import com.tobeto.pair3.services.dtos.responses.GetCarResponse;

import java.util.List;

public interface CarService {

    public void add(CreateCarRequest createCarRequest);

    void update(UpdateCarRequest updateCarRequest);

    void delete(Integer id);

    List<GetCarResponse> getAll();

    GetCarResponse getById(Integer id);


    boolean existsById(int carId);

    Car getOriginalCarById(int carId);

    List<CarModelResponse> getRentableCars(RentableRequest rentableRequest);

    List<CarModelResponse> filterCars(FilterCarRequest filterCarRequest);

    List<CarModelResponse> sortCars(SortCarsRequest sortCarsRequest);

    List<Car> findByModelId(int modelId);
}
