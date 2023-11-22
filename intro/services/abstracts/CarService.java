package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<CarResponse> getAllCars();
    CarResponse getCarById(int id);
    void createCar(CreateCarRequest carRequest);
    void updateCar(UpdateCarRequest updatedCarRequest);
    void deleteCar(int id);

    Car getOrginalCarById(int carId);
}