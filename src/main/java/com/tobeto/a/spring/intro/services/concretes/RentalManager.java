package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.Hes;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.repositories.RentalRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.abstracts.PaymentService;
import com.tobeto.a.spring.intro.services.abstracts.RentalService;
import com.tobeto.a.spring.intro.services.abstracts.UserService;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.CreateRentalRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateRentalRequest;
import com.tobeto.a.spring.intro.services.models.responses.RentalResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
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
    public void createRental(List<CreateRentalRequest> rentalRequests) {



       rentalRequests.stream().forEach(rentalRequest -> {

           if (isReservable(rentalRequest.getRentalDate(),rentalRequest.getReturnDate(),rentalRequest.getStartCarSupplier(),rentalRequest.getFinishCarSupplier(),
                   rentalRequest.getCarId())){
           Rental rental = new Rental();
           rental.setRentalDate(rentalRequest.getRentalDate());
           rental.setReturnDate(rentalRequest.getReturnDate());
           rental.setStartCarSupplier(rentalRequest.getStartCarSupplier());
           rental.setFinishCarSupplier(rentalRequest.getFinishCarSupplier());
           rental.setCar(carService.getOrginalCarById(rentalRequest.getCarId()));
           rental.setUser(userService.getOriginalUserById(rentalRequest.getUserId()));


           rentalRepository.save(rental);}});


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

    @Override
    @Transactional
    public void makePayment(CreatePaymentRequest paymentRequest) {

        Rental rental= rentalRepository.findById(paymentRequest.getRentalId()).orElseThrow();
        Payment payment=Payment.builder().paymentAmount(paymentRequest.getPaymentAmount()).paymentDate(paymentRequest.getPaymentDate())
                .paymentOption(paymentRequest.getPaymentOption()).build();

        rental.setPayment(payment);
        payment.setRental(rental);
        

        Rental rental1=rentalRepository.save(rental);


    }


    private RentalResponse convertToResponse(Rental rental) {
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setRentalDate(rental.getRentalDate());
        rentalResponse.setReturnDate(rental.getReturnDate());
        rentalResponse.setCarName(rental.getCar().getModelName());
        if(rental.getPayment()!=null){
        rentalResponse.setPaymentValue(rental.getPayment().getPaymentAmount());}
        rentalResponse.setUserName(rental.getUser().getName());


        return rentalResponse;
    }
    public  boolean isReservable(LocalDate rentalDate, LocalDate returnDate, String startCarSupplier, String finishCarSupplier, int  carId) {


        Rental rental1=null;
        Rental rental2=null;
        boolean isSuitable=false;

        Car car=carService.getOrginalCarById(carId);

        List<Rental> rentals=car.getRentals();
        if(rentals.size()==0)return true;

        Collections.sort(rentals, Comparator.comparing(Rental::getRentalDate));

        if (rentals.size()==1){

            if ((rentals.get(0).getReturnDate().isBefore(rentalDate) && rentals.get(0).getFinishCarSupplier().equals(startCarSupplier))
                    || (rentals.get(0).getRentalDate().isAfter(returnDate)&& rentals.get(0).getStartCarSupplier().equals(finishCarSupplier) ))return true;
        }



        for (int i = 0; i <rentals.size()-1 ; i++) {

            if(rentalDate.isAfter(rentals.get(i).getReturnDate()) && returnDate.isBefore(rentals.get(i+1).getRentalDate())){
                rental1=car.getRentals().get(i);
                rental2=car.getRentals().get(i+1);

                break;
            }
            if(i==rentals.size()-2 && rentals.get(i+1).getReturnDate().isBefore(rentalDate) ){

                rental2=rentals.get(i+1);
                break;
            }
            if(i==0 && rentals.get(i).getRentalDate().isAfter(returnDate) ){

                rental2=rentals.get(i);
                break;
            }

        }
        if(rental1==null && rental2!=null){if (rental2.getFinishCarSupplier().equals(startCarSupplier) ||
                (rental2.getStartCarSupplier().equals(finishCarSupplier) && rental2.equals(rentals.get(0))) )isSuitable=true;}
        else {
            if(rental1!=null)
            if (rental1.getFinishCarSupplier().equals(startCarSupplier) &&rental2.getStartCarSupplier().equals(finishCarSupplier) )isSuitable=true;
        }


        return isSuitable;



    }
}

