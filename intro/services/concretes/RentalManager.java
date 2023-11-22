package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.repositories.RentalRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.abstracts.PaymentService;
import com.tobeto.a.spring.intro.services.abstracts.RentalService;
import com.tobeto.a.spring.intro.services.abstracts.UserService;
import com.tobeto.a.spring.intro.services.models.requests.CreateRentalRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateRentalRequest;
import com.tobeto.a.spring.intro.services.models.responses.RentalResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;

    private final CarService carService;

    private final UserService userService;

    private final PaymentService paymentService;


    public RentalManager(RentalRepository rentalRepository, CarService carService, UserService userService, @Lazy PaymentService paymentService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Override
    public List<RentalResponse> getAllRentals() {
        List<Rental> rentalList = rentalRepository.findAll();
        return rentalList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RentalResponse getRentalById(int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
        return convertToResponse(rental);
    }

    @Override
    public void createRental(CreateRentalRequest rentalRequest) {

        Rental rental = new Rental();
        rental.setRentalDate(rentalRequest.getRentalDate());
        rental.setReturnDate(rentalRequest.getReturnDate());
        rental.setCar(carService.getOrginalCarById(rentalRequest.getCarId()));
        rental.setUser(userService.getOriginalUserById(rentalRequest.getUserId()));
        int id=paymentService.createPayment(rentalRequest.getCreatePaymentRequest());

        rental.setPayment(paymentService.getOriginalPaymentById(id));
        int id2=rentalRepository.save(rental).getId();
        UpdatePaymentRequest updatePaymentRequest=new UpdatePaymentRequest();
        updatePaymentRequest.setId(id);
        updatePaymentRequest.setRentalId(id2);
        updatePaymentRequest.setPaymentAmount(rental.getPayment().getPaymentAmount());
        updatePaymentRequest.setPaymentDate(rental.getPayment().getPaymentDate());
        updatePaymentRequest.setPaymentOption(rental.getPayment().getPaymentOption());
        paymentService.updatePayment(updatePaymentRequest);
    }

    @Override
    public void updateRental(UpdateRentalRequest updatedRentalRequest) {

        Optional<Rental> existingRental = rentalRepository.findById(updatedRentalRequest.getId());

        if (existingRental.isPresent()) {
            Rental rental = existingRental.get();
            rental.setRentalDate(updatedRentalRequest.getRentalDate());
            rental.setReturnDate(updatedRentalRequest.getReturnDate());

            rentalRepository.save(rental);
        } else {
            throw new RuntimeException("There is no rental");
        }
    }



    @Override
    public void deleteRental(int id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rental not found with id: " + id);
        }
    }

    @Override
    public Rental getOriginalRentalById(int rentalId) {
        return rentalRepository.findById(rentalId).orElseThrow();
    }

    private RentalResponse convertToResponse(Rental rental) {
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setRentalDate(rental.getRentalDate());
        rentalResponse.setReturnDate(rental.getReturnDate());
        rentalResponse.setCarName(rental.getCar().getModelName());
        rentalResponse.setPaymentValue(rental.getPayment().getPaymentAmount());
        rentalResponse.setUserName(rental.getUser().getName());


        return rentalResponse;
    }
}

