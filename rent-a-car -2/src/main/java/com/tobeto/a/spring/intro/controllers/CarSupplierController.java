package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.repositories.CarSupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carSuppliers")
public class CarSupplierController {

    private final CarSupplierRepository carSupplierRepository;

    public CarSupplierController(CarSupplierRepository carSupplierRepository) {
        this.carSupplierRepository = carSupplierRepository;
    }

    @GetMapping
    public List<CarSupplier> getAllCarSuppliers() {
        return carSupplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public CarSupplier getCarSupplierById(@PathVariable int id) {
        return carSupplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarSupplier not found with id: " + id));
    }

    @PostMapping
    public CarSupplier createCarSupplier(@RequestBody CarSupplier carSupplier) {
        return carSupplierRepository.save(carSupplier);
    }

    @PutMapping
    public CarSupplier updateCarSupplier( @RequestBody CarSupplier updatedCarSupplier) {
        Optional<CarSupplier> existingCarSupplier = carSupplierRepository.findById(updatedCarSupplier.getId());

        if (existingCarSupplier.isPresent()) {
            CarSupplier carSupplier = existingCarSupplier.get();
            carSupplier.setName(updatedCarSupplier.getName());
            carSupplier.setLocation(updatedCarSupplier.getLocation());

            return carSupplierRepository.save(carSupplier);
        } else {
            throw new RuntimeException("CarSupplier not found with id: " + updatedCarSupplier.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCarSupplier(@PathVariable int id) {
        if (carSupplierRepository.existsById(id)) {
            carSupplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("CarSupplier not found with id: " + id);
        }
    }
}

