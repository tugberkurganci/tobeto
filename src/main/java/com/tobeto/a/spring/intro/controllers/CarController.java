package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.repositories.CarRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
import com.tobeto.a.spring.intro.services.models.requests.GetAvaibleCarsRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarResponse getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public void createCar(@RequestBody List<CreateCarRequest> carRequest) {
        carService.createCar(carRequest);


    }

    @PutMapping
    public void updateCar(@RequestBody UpdateCarRequest updatedCarRequest) {
        carService.updateCar(updatedCarRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }

    @GetMapping("/filteredCars")
    public List<CarResponse> findCars(
            @RequestParam( required = false) Integer modelYear1,
            @RequestParam( required = false) Integer modelYear2,
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) Integer dailyPrice1,
            @RequestParam(required = false) Integer dailyPrice2,
            @RequestParam( required = false) String color,
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String status
    ) {
        return carService.findFilteredCars(modelYear1, modelYear2, modelName, dailyPrice1, dailyPrice2, color, brandName,status);
    }

    @GetMapping("/filteredCarsInMemory")
    public List<CarResponse> findCarsInMemory(
            @RequestParam( required = false) Integer modelYear1,
            @RequestParam( required = false) Integer modelYear2,
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) Integer dailyPrice1,
            @RequestParam(required = false) Integer dailyPrice2,
            @RequestParam( required = false) String color,
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String status
    ) {
        return carService.findFilteredCarsInMemory(modelYear1, modelYear2, modelName, dailyPrice1, dailyPrice2, color, brandName,status);
    }

    @PostMapping("/sortedCars")
    public List<CarResponse> getSortedCarsForList(@RequestBody List<CarResponse> carList,
                                                  @RequestParam String sortBy) {
            return carService.sortCars(carList,sortBy );

    }
    @GetMapping("/popularCars")
    List<CarResponse> sortedWhichCarRentedMore(){

        return carService.sortedWhichCarRentedMore();
    }

    @PostMapping("/getAvaibleCars")
     public List<CarResponse> getAvaibleCars(@RequestBody GetAvaibleCarsRequest getAvaibleCarsRequest){

        return carService.getAvaibleCars(getAvaibleCarsRequest);
    }
}
