package com.tobeto.pair3.controllers;

import com.tobeto.pair3.services.abstracts.RentalService;
import com.tobeto.pair3.services.dtos.requests.CreateRentalRequest;
import com.tobeto.pair3.services.dtos.requests.UpdateRentalRequest;
import com.tobeto.pair3.services.dtos.responses.GetInvoiceResponse;
import com.tobeto.pair3.services.dtos.responses.GetRentalResponse;
import com.tobeto.pair3.services.dtos.responses.RentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<GetRentalResponse> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("{id}")
    public GetRentalResponse getById(@PathVariable("id") int id) {
        return rentalService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<RentalResponse> getRentalsByUserId(@PathVariable("userId") int userId) {
        return rentalService.getRentalsByUserId(userId);
    }

    @PostMapping
    public GetInvoiceResponse add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
        return rentalService.add(createRentalRequest);
    }




    //1

    @PatchMapping("/updatedOverdue")
    public void updateStatusToOverdue() {
        rentalService.updateStatusToOverdue();
    }

    //2

    @PatchMapping("/changeCar")
    public void cancelRentalIfBeforeRentalIsOverDueAndChangeCar() {
        rentalService.cancelRentalIfBeforeRentalIsOverDueAndChangeCar();
    }


    @PutMapping
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        rentalService.update(updateRentalRequest);
    }



    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        rentalService.delete(id);
    }
}
