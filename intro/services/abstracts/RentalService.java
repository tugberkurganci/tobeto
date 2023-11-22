package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.services.models.requests.CreateRentalRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateRentalRequest;
import com.tobeto.a.spring.intro.services.models.responses.RentalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    List<RentalResponse> getAllRentals();
    RentalResponse getRentalById(int id);
    void createRental(CreateRentalRequest rentalRequest);
    void updateRental(UpdateRentalRequest updatedRentalRequest);
    void deleteRental(int id);

    Rental getOriginalRentalById(int rentalId);
}
