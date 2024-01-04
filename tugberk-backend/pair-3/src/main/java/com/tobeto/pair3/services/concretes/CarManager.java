package com.tobeto.pair3.services.concretes;

import com.tobeto.pair3.core.exception.BusinessException;
import com.tobeto.pair3.core.utils.mapper.ModelMapperService;
import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.entities.Rental;
import com.tobeto.pair3.repositories.CarRepository;
import com.tobeto.pair3.services.abstracts.CarService;
import com.tobeto.pair3.services.abstracts.ColorService;
import com.tobeto.pair3.services.abstracts.ModelService;
import com.tobeto.pair3.services.dtos.requests.*;
import com.tobeto.pair3.services.dtos.responses.CarModelResponse;
import com.tobeto.pair3.services.dtos.responses.GetCarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;
    private final ModelMapperService mapperService;


    public void add(CreateCarRequest createCarRequest) {
        if (carRepository.existsByPlate(createCarRequest.getPlate())) {
            throw new RuntimeException("aynı plaka mevcut");
        }
        if (!colorService.existsColorById(createCarRequest.getColorId())) {
            throw new RuntimeException("Böyle bir renk yok");
        }
        modelService.getById(createCarRequest.getModelId());
        Car car = mapperService.forRequest().map(createCarRequest, Car.class);
        carRepository.save(car);
    }

    public void update(UpdateCarRequest updateCarRequest) {

        Car carToUpdate = carRepository.findById(updateCarRequest.getId()).orElseThrow();

        mapperService.forRequest().map(updateCarRequest, carToUpdate);

        carRepository.save(carToUpdate);
    }

    public void delete(Integer id) {
        Car car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
    }

    public List<GetCarResponse> getAll() {
        List<Car> carList = carRepository.findAll();
        List<GetCarResponse> responseList = carList.stream()
                .map(car -> mapperService.forResponse().map(car, GetCarResponse.class))
                .toList();
        return responseList;
    }

    public GetCarResponse getById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse response = mapperService.forResponse().map(car, GetCarResponse.class);
        return response;
    }

    @Override
    public boolean existsById(int carId) {
        return carRepository.existsById(carId);
    }

    @Override
    public Car getOriginalCarById(int carId) {
        return carRepository.findById(carId).orElseThrow();
    }

    @Override
    public List<CarModelResponse> getRentableCars(RentableRequest rentableRequest) {
        List<Car> carList = carRepository.findAll();
        List<Car> carList2 = new ArrayList<>();

        carList.forEach(car -> {
            if(rentableRequest.getDropoffLocation().equals(""))rentableRequest.setDropoffLocation(rentableRequest.getPickupLocation());


            boolean isSuitable = isReservable(rentableRequest, car.getId());

            if (isSuitable){setTotalPriceToCar(car.getDailyPrice(),rentableRequest); carList2.add(car);}

        });
        List<CarModelResponse> newCarList=carList2.stream()
                .map(CarModelResponse::new)
                .toList();

        Map<String, CarModelResponse> distinctModels = new HashMap<>();

        for (CarModelResponse car : newCarList) {
            distinctModels.computeIfAbsent(car.getModel(), k -> car);

        }

        return new ArrayList<>(distinctModels.values());



    }
    private BigDecimal setTotalPriceToCar(BigDecimal dailyPrice, RentableRequest rentableRequest) {

        long rentalTime= ChronoUnit.DAYS.between(rentableRequest.getPickupDate(),rentableRequest.getDropoffDate());
        BigDecimal totalPrice = dailyPrice.multiply(new BigDecimal(rentalTime));
       return totalPrice;
    }

    @Override
    public List<CarModelResponse> filterCars(FilterCarRequest filterCarRequest) {

        List<CarModelResponse> filteredCars = new ArrayList<>();

        for (CarModel car : filterCarRequest.getCarList()) {


            if (!filterCarRequest.getModel().equals("") && !car.getModel().equalsIgnoreCase(filterCarRequest.getModel())) {
                continue;
            }

            if (!filterCarRequest.getBrand().equals("") && !car.getBrand().equalsIgnoreCase(filterCarRequest.getBrand())) {
                continue;
            }
            if (filterCarRequest.getMinPrice() != 0 && car.getPrice().compareTo(new BigDecimal(filterCarRequest.getMinPrice())) < 0 ){
                continue;
            }
            if (filterCarRequest.getMaxPrice() != 0 && car.getPrice().compareTo(new BigDecimal(filterCarRequest.getMaxPrice())) > 0 ){
                continue;
            }
            CarModel carModel=new CarModel();

            filteredCars.add(carModel.getResponse(car));
        }

        return  filteredCars;

    }

    @Override
    public List<CarModelResponse> sortCars(SortCarsRequest sortCarsRequest) {


        Comparator<CarModelResponse> comparator;

        switch (sortCarsRequest.getSortType()) {
            case "price-asc":
                comparator = Comparator.comparing(CarModelResponse::getPrice);
                break;
            case "price-desc":
                comparator = Comparator.comparing(CarModelResponse::getPrice).reversed();
                break;

            default:
                throw new IllegalArgumentException("Invalid sort option");
        }
        List<CarModelResponse> beforeSortedList=new ArrayList<>();
        for (CarModel c:sortCarsRequest.getCarList()
             ) {
            CarModel carModel=new CarModel();
            CarModelResponse carModelResponse=carModel.getResponse(c);
            beforeSortedList.add(carModelResponse);
        }

        return beforeSortedList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findByModelId(int modelId) {
        return carRepository.findByModelId(modelId);
    }

    public boolean isReservable(RentableRequest rentableRequest, int carId) {


        Rental rental1 = null;
        Rental rental2 = null;
        boolean isSuitable = false;
        Car car = this.getOrginalCarById(carId);


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

    private Car getOrginalCarById(int carId) {
        return carRepository.findById(carId).orElseThrow(() -> new BusinessException("no car with this car ıd"));
    }
}
