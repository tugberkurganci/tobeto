package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.repositories.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }


    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }


    @PutMapping
    public Car updateCar( @RequestBody Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(updatedCar.getId());

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setModelYear(updatedCar.getModelYear());
            car.setModelName(updatedCar.getModelName());
            car.setDailyPrice(updatedCar.getDailyPrice());
            car.setColor(updatedCar.getColor());
            car.setStatus(updatedCar.getStatus());


            return carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found with id: " + updatedCar.getId());
        }
    }


    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Car not found with id: " + id);
        }
    }
}
