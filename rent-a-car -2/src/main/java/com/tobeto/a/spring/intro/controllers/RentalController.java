package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.repositories.RentalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;

    public RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable int id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalRepository.save(rental);
    }

    @PutMapping
    public Rental updateRental( @RequestBody Rental updatedRental) {
        Optional<Rental> existingRental = rentalRepository.findById(updatedRental.getId());

        if (existingRental.isPresent()) {
            Rental rental = existingRental.get();
            rental.setRentalDate(updatedRental.getRentalDate());
            rental.setReturnDate(updatedRental.getReturnDate());
            // payment ve user ilişkilerini güncelleme (eğer gerekirse)
            rental.setPayment(updatedRental.getPayment());
            rental.setUser(updatedRental.getUser());
            // cars ilişkisini güncelleme (eğer gerekirse)
            rental.setCars(updatedRental.getCars());

            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental not found with id: " + updatedRental.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable int id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rental not found with id: " + id);
        }
    }
}
