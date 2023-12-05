package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
import com.tobeto.a.spring.intro.services.models.requests.GetAvaibleCarsRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<CarResponse> getAllCars();
    CarResponse getCarById(int id);
    void createCar(List<CreateCarRequest> carRequest);
    void updateCar(UpdateCarRequest updatedCarRequest);
    void deleteCar(int id);

    Car getOrginalCarById(int carId);

    List<CarResponse> findFilteredCars(Integer modelYear1, Integer modelYear2, String modelName, Integer dailyPrice1, Integer dailyPrice2, String color, String brandName,String status);

    List<CarResponse> findFilteredCarsInMemory(Integer modelYear1, Integer modelYear2, String modelName, Integer dailyPrice1, Integer dailyPrice2, String color, String brandName,String status);

    public List<CarResponse> sortCars(List<CarResponse> cars, String sortBy);

    List<CarResponse> sortedWhichCarRentedMore();

    List<CarResponse> getAvaibleCars(GetAvaibleCarsRequest getAvaibleCarsRequest);
}