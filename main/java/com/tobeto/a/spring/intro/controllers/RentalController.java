package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.services.abstracts.RentalService;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.CreateRentalRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateRentalRequest;
import com.tobeto.a.spring.intro.services.models.responses.RentalResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {


    private final RentalService rentalService;
    int counter=1;


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
    public int createRental(@RequestBody List<CreateRentalRequest> rentalRequest) {
        System.out.println("istek ");
        return rentalService.createRental(rentalRequest);
    }

    @PutMapping
    public void updateRental(@RequestBody UpdateRentalRequest updatedRentalRequest) {
        rentalService.updateRental(updatedRentalRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }

    @PostMapping("/payment")
    public void makePayment( @RequestBody CreatePaymentRequest paymentRequest) {


        rentalService.makePayment(paymentRequest);
    }

    @PostMapping("/calculateReservationPrice")
    public double calculateReservationPrice( @RequestBody CreateRentalRequest rentalRequest) {


       return rentalService.calculateReservationPrice(rentalRequest);
    }




}


