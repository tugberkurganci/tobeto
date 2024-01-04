package com.tobeto.pair3.services.concretes;

import com.tobeto.pair3.core.exception.BusinessException;
import com.tobeto.pair3.core.utils.mapper.ModelMapperService;
import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.entities.Rental;
import com.tobeto.pair3.repositories.RentalRepository;
import com.tobeto.pair3.services.abstracts.CarService;
import com.tobeto.pair3.services.abstracts.RentalService;
import com.tobeto.pair3.services.abstracts.UserService;
import com.tobeto.pair3.services.businessrules.RentalRules;
import com.tobeto.pair3.services.dtos.requests.CreateRentalRequest;
import com.tobeto.pair3.services.dtos.requests.UpdateRentalRequest;
import com.tobeto.pair3.services.dtos.responses.GetCarResponse;
import com.tobeto.pair3.services.dtos.responses.GetRentalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService mapperService;

    private final CarService carService;

    private final UserService userService;

    private final RentalRules rentalRules;





    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(CreateRentalRequest createRentalRequest) {

        if(createRentalRequest.getDropoffLocation().equals(""))createRentalRequest.setDropoffLocation(createRentalRequest.getPickupLocation());



        List<Car> carList=carService.findByModelId(createRentalRequest.getModelId());

        Optional<Car> rentedCar=carList.stream().filter(car -> isReservable(createRentalRequest,car.getId())).findFirst();

        if(rentedCar.isPresent()){
            createRentalRequest.setCarId(rentedCar.get().getId());
        }else {throw new BusinessException("this car not suitable");}

         rentalRules.checkIsDateBeforeNow(createRentalRequest.getPickupDate());

        rentalRules.checkEndDateIsBeforeStartDate(createRentalRequest.getDropoffDate(),createRentalRequest.getPickupDate());

        checkIsCarExist(createRentalRequest.getCarId());

        checkIsUserExists(createRentalRequest.getUserId());

        rentalRules.checkIsRentalDateLongerThan25Days(createRentalRequest.getPickupDate(),createRentalRequest.getDropoffDate());

        Rental rental = mapperService.forRequest().map(createRentalRequest, Rental.class);

        rental.setStartDate(createRentalRequest.getPickupDate());
        rental.setReturnDate(createRentalRequest.getDropoffDate());


        setActualKılometerToRentalInfo(rental,createRentalRequest);

        setTotalPriceToRentalInfo(rental,createRentalRequest);

        rentalRepository.save(rental);
    }


    public void update(UpdateRentalRequest updateRentalRequest) {
        Rental rentalToUpdate = rentalRepository.findById(updateRentalRequest.getId()).orElseThrow(() -> new BusinessException("there is no rental"));
        Rental rental=new Rental();

        if(updateRentalRequest.getReturnDate()!=null){
            rental.setReturnDate(updateRentalRequest.getReturnDate());
        }
        if(updateRentalRequest.getEndKilometer()!=0){
            rental.setEndKilometer(updateRentalRequest.getEndKilometer());
        }
        if(updateRentalRequest.getTotalPrice()!=null){
            rental.setTotalPrice(updateRentalRequest.getTotalPrice());
        }
        if(updateRentalRequest.getUserId()!=0){
            rental.setUser(userService.getOriginalUserById(updateRentalRequest.getUserId()));
        }

        if(updateRentalRequest.getCarId()!=0){
            rental.setCar(carService.getOriginalCarById(updateRentalRequest.getCarId()));
        }
        rental.setStartKilometer(rentalToUpdate.getStartKilometer());
        rental.setStartDate(rentalToUpdate.getStartDate());
        rental.setEndDate(rentalToUpdate.getEndDate());


        checkIsCarExist(rentalToUpdate.getUser().getId());
        checkIsUserExists(rentalToUpdate.getUser().getId());


        rentalRepository.save(rentalToUpdate);
    }

    public void delete(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rentalRepository.delete(rental);
    }

    public List<GetRentalResponse> getAll() {
        List<Rental> rentalList = rentalRepository.findAll();
        List<GetRentalResponse> responseList = rentalList.stream()
                .map(rental -> mapperService.forResponse().map(rental, GetRentalResponse.class))
                .toList();
        return responseList;
    }

    public GetRentalResponse getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse response = mapperService.forResponse().map(rental, GetRentalResponse.class);
        return response;
    }


    //////////////////////////////////////////////////////////////////////////////////
    ///////////////////////// Business Methods//////////////////////////////////////

    private void checkIsUserExists(int userId) {
        if(!userService.existsById(userId)) {
            throw new RuntimeException("there is no user with this id");
        }
    }

    private void checkIsCarExist(int carId) {
        if (!carService.existsById(carId)) {
            throw new RuntimeException("there is no car with this id");
        }
    }

    private void setTotalPriceToRentalInfo(Rental rental, CreateRentalRequest createRentalRequest) {
        GetCarResponse response2= carService.getById(createRentalRequest.getCarId());
        BigDecimal dailyPrice=response2.getDailyPrice();
        long rentalTime= ChronoUnit.DAYS.between(createRentalRequest.getPickupDate(),createRentalRequest.getDropoffDate());
        BigDecimal totalPrice = dailyPrice.multiply(new BigDecimal(rentalTime));
        rental.setTotalPrice(totalPrice);
    }

    private void setActualKılometerToRentalInfo(Rental rental, CreateRentalRequest createRentalRequest) {
        GetCarResponse response= carService.getById(createRentalRequest.getCarId());
        Integer actualKilometers=response.getKilometer();
        rental.setStartKilometer(actualKilometers);
    }

    public boolean isReservable(CreateRentalRequest  rentableRequest, int carId) {


        Rental rental1 = null;
        Rental rental2 = null;
        boolean isSuitable = false;
        Car car = carService.getOriginalCarById(carId);


        List<Rental> rentals = car.getRentals();
        if (rentals.size() == 0 &&
                car.getCurrentOffice().equals(rentableRequest.getPickupLocation())) return true;
        if (rentals.size() == 0) return false;


        rentals.sort(Comparator.comparing(Rental::getStartDate));

        if (rentals.size() == 1) {
            if ((rentals.get(0).getReturnDate().isBefore(rentableRequest.getPickupDate()) && rentals.get(0).getDropOffLocation().equals(rentableRequest.getPickupLocation()))
                    || (rentals.get(0).getStartDate().isAfter(rentableRequest.getDropoffDate()) && rentals.get(0).getPickupLocation().equals(rentableRequest.getDropoffLocation())))
                return true;
        }


        for (int i = 0; i < rentals.size() - 1; i++) {

            if (rentableRequest.getPickupDate().isAfter(rentals.get(i).getReturnDate()) && rentableRequest.getDropoffDate().isBefore(rentals.get(i + 1).getStartDate())) {
                rental1 = car.getRentals().get(i);
                rental2 = car.getRentals().get(i + 1);

                break;
            }
            if (i == rentals.size() - 2 && rentals.get(i + 1).getReturnDate().isBefore(rentableRequest.getPickupDate())) {

                rental2 = rentals.get(i + 1);
                break;
            }
            if (i == 0 && rentals.get(i).getStartDate().isAfter(rentableRequest.getDropoffDate())) {

                rental2 = rentals.get(i);
                break;
            }

        }
        if (rental1 == null && rental2 != null) {
            if (rental2.getDropOffLocation().equals(rentableRequest.getPickupLocation()) ||
                    (rental2.getPickupLocation().equals(rentableRequest.getDropoffLocation()) && rental2.equals(rentals.get(0))))
                isSuitable = true;
        } else {
            if (rental1 != null)
                if (rental1.getDropOffLocation().equals(rentableRequest.getPickupLocation()) && rental2.getPickupLocation().equals(rentableRequest.getDropoffLocation()))
                    isSuitable = true;
        }

        return isSuitable;


    }


}
