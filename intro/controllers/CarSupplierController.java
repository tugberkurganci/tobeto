package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.repositories.CarSupplierRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarSupplierService;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarSupplierResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-suppliers")
public class CarSupplierController {

    private final CarSupplierService carSupplierService;


    public CarSupplierController(CarSupplierService carSupplierService) {
        this.carSupplierService = carSupplierService;
    }

    @GetMapping
    public List<CarSupplierResponse> getAllCarSuppliers() {
        return carSupplierService.getAllCarSuppliers();
    }

    @GetMapping("/{id}")
    public CarSupplierResponse getCarSupplierById(@PathVariable int id) {
        return carSupplierService.getCarSupplierById(id);
    }

    @PostMapping
    public void createCarSupplier(@RequestBody CreateCarSupplierRequest carSupplierRequest) {
         carSupplierService.createCarSupplier(carSupplierRequest);
    }

    @PutMapping
    public void updateCarSupplier(@RequestBody UpdateCarSupplierRequest updatedCarSupplierRequest) {
        carSupplierService.updateCarSupplier(updatedCarSupplierRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteCarSupplier(@PathVariable int id) {
        carSupplierService.deleteCarSupplier(id);
    }
}


