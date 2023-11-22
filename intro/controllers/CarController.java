package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.repositories.CarRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
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
    public void createCar(@RequestBody CreateCarRequest carRequest) {
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
}
