package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.repositories.RentalRepository;
import com.tobeto.a.spring.intro.services.abstracts.RentalService;
import com.tobeto.a.spring.intro.services.models.requests.CreateRentalRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateRentalRequest;
import com.tobeto.a.spring.intro.services.models.responses.RentalResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;


    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalResponse getRentalById(@PathVariable int id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public void createRental(@RequestBody CreateRentalRequest rentalRequest) {
        rentalService.createRental(rentalRequest);
    }

    @PutMapping
    public void updateRental(@RequestBody UpdateRentalRequest updatedRentalRequest) {
        rentalService.updateRental(updatedRentalRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }
}


