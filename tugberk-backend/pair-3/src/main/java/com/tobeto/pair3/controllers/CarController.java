package com.tobeto.pair3.controllers;

import com.tobeto.pair3.services.abstracts.CarService;
import com.tobeto.pair3.services.dtos.requests.*;
import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import com.tobeto.pair3.services.dtos.responses.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<GetCarResponse> getAll() {
        return carService.getAll();
    }

    @PostMapping("/rentable")
    public List<CarModelResponse> getRentableCars(@RequestBody @Valid RentableRequest rentableRequest) {
        return carService.getRentableCars(rentableRequest);
    }
    @PostMapping("/filter")
    public List<CarModelResponse> filterCars(@RequestBody @Valid FilterCarRequest filterCarRequest) {
        return carService.filterCars(filterCarRequest);
    }
    @PostMapping("/sort")
    public List<CarModelResponse> sortCars(@RequestBody @Valid SortCarsRequest sortCarsRequest) {
        return carService.sortCars(sortCarsRequest);
    }

    @GetMapping("{id}")
    public GetCarResponse getById(@PathVariable("id") Integer id) {
        return carService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid CreateCarRequest createCarRequest) {
        carService.add(createCarRequest);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        carService.update(updateCarRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        carService.delete(id);
    }


}
