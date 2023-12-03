package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.Hes;
import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.repositories.CarRepository;
import com.tobeto.a.spring.intro.services.abstracts.BrandService;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.abstracts.CarSupplierService;
import com.tobeto.a.spring.intro.services.models.requests.GetAvaibleCarsRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarRequest;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final CarSupplierService supplierService;
    private final BrandService brandService;


    public CarManager(CarRepository carRepository, CarSupplierService supplierService, BrandService brandService) {
        this.carRepository = carRepository;
        this.supplierService = supplierService;
        this.brandService = brandService;
    }

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> carList = carRepository.findAll();
        return carList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return convertToResponse(car);
    }

    @Override
    public void createCar(List<CreateCarRequest> carRequest1) {
      carRequest1.stream().forEach(carRequest ->  { Car car = new Car();
        car.setModelYear(carRequest.getModelYear());
        car.setModelName(carRequest.getModelName());
        car.setDailyPrice(carRequest.getDailyPrice());
        car.setColor(carRequest.getColor());
        car.setStatus(carRequest.getStatus());
        CarSupplier carSupplier=supplierService.getOriginalCarSupplierById(carRequest.getCarSupplierId());
        car.setCarSupplier(carSupplier);
        Brand brand=brandService.getOriginalBrandById(carRequest.getBrandId());
        car.setBrand(brand);

        carRepository.save(car);});
    }

    @Override
    public void updateCar(UpdateCarRequest updatedCarRequest) {
        Optional<Car> existingCar = carRepository.findById(updatedCarRequest.getId());

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setModelYear(updatedCarRequest.getModelYear());
            car.setModelName(updatedCarRequest.getModelName());
            car.setDailyPrice(updatedCarRequest.getDailyPrice());
            car.setColor(updatedCarRequest.getColor());
            car.setStatus(updatedCarRequest.getStatus());
            if (updatedCarRequest.getCarSupplierId()!=0) {
                car.setCarSupplier(supplierService.getOriginalCarSupplierById(updatedCarRequest.getCarSupplierId()));
            }

            carRepository.save(car);
        } else {
            throw new RuntimeException("There is no car");
        }
    }

    @Override
    public void deleteCar(int id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Car not found with id: " + id);
        }
    }

    @Override
    public Car getOrginalCarById(int carId) {
        return carRepository.findById(carId).orElseThrow();
    }

    @Override
    public List<CarResponse> findFilteredCars(Integer modelYear1, Integer modelYear2, String modelName, Integer dailyPrice1, Integer dailyPrice2, String color, String brandName,String status) {
        return carRepository.findByDynamicFilters(modelYear1, modelYear2, modelName, dailyPrice1, dailyPrice2, color, brandName,status);
    }

    @Override
    public List<CarResponse> findFilteredCarsInMemory(Integer modelYear1, Integer modelYear2, String modelName, Integer dailyPrice1, Integer dailyPrice2, String color, String brandName, String status) {

        List<CarResponse> cars = this.getAllCars();
        List<CarResponse> filteredCars = new ArrayList<>();


        for (CarResponse car : cars) {

            if (modelYear1 != null && car.getModelYear() < modelYear1) {
                continue;
            }
            if (modelYear2 != null && car.getModelYear() > modelYear2) {
                continue;
            }
            if (modelName != null && !car.getModelName().equalsIgnoreCase(modelName)) {
                continue;
            }
            if (dailyPrice1 != null && car.getDailyPrice() < dailyPrice1) {
                continue;
            }
            if (dailyPrice2 != null && car.getDailyPrice() > dailyPrice2) {
                continue;
            }
            if (color != null && !car.getColor().equalsIgnoreCase(color)) {
                continue;
            }
            if (brandName != null && !car.getBrandName().equalsIgnoreCase(brandName)) {
                continue;
            }
            if (status != null && !car.getStatus().equalsIgnoreCase(status)) {
                continue;
            }

            filteredCars.add(car);
        }

        return filteredCars;
    }
    public List<CarResponse> sortCars(List<CarResponse> cars, String sortBy) {
        Comparator<CarResponse> comparator;

        switch (sortBy) {
            case "price-asc":
                comparator = Comparator.comparingDouble(CarResponse::getDailyPrice);
                break;
            case "price-desc":
                comparator = Comparator.comparingDouble(CarResponse::getDailyPrice).reversed();
                break;
            case "name-asc":
                comparator = Comparator.comparing(CarResponse::getModelName);
                break;
            case "name-desc":
                comparator = Comparator.comparing(CarResponse::getModelName).reversed();
                break;
            case "year-asc":
                comparator = Comparator.comparingInt(CarResponse::getModelYear);
                break;
            case "year-desc":
                comparator = Comparator.comparingInt(CarResponse::getModelYear).reversed();
                break;


            default:
                throw new IllegalArgumentException("Invalid sort option");
        }

        return cars.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> sortedWhichCarRentedMore() {
        return carRepository.sortedWhichCarRentedMore();
    }

    @Override
    public List<CarResponse> getAvaibleCars(GetAvaibleCarsRequest getAvaibleCarsRequest) {

        List<Car> carList=carRepository.findAll();
        List<Car> carList2=new ArrayList<>();

        carList.stream().forEach(car -> {


            boolean isSuitable=false;


            if(isReservable(getAvaibleCarsRequest.getRentalDate(),getAvaibleCarsRequest.getReturnDate(),getAvaibleCarsRequest.getStartCarSupplier(),
                            getAvaibleCarsRequest.getFinishCarSupplier(),car.getId())
            )
            {isSuitable=true;};

           if (isSuitable)carList2.add(car);

        });
    return carList2.stream().map(this::convertToResponse).collect(Collectors.toList());
    }


    /*
    @Override
    public List<CarResponse> getAvaibleCars(GetAvaibleCarsRequest getAvaibleCarsRequest) {

        List<Car> carList=carRepository.findAll();
        List<Car> carList2=new ArrayList<>();
        Map<List<LocalDate>,List<Integer>> map=new TreeMap<>();
        carList.stream().forEach(car -> {
            List<LocalDate> reservations=new ArrayList<>();

            boolean isSuitable=false;

            car.getRentals().stream().forEach(
                    rental -> {
                        Hes.collectReservationDays(rental.getRentalDate(),rental.getReturnDate()).forEach(localDate -> reservations.add(localDate));

                    }
            );

            if(Hes.isReservable(getAvaibleCarsRequest.getRentalDate(),getAvaibleCarsRequest.getReturnDate(),reservations) &&
                    Hes.findNearByRentals(getAvaibleCarsRequest.getRentalDate(),getAvaibleCarsRequest.getReturnDate(),getAvaibleCarsRequest.getStartCarSupplier(),
                            getAvaibleCarsRequest.getFinishCarSupplier(),car))
            {isSuitable=true;};
           if (isSuitable)carList2.add();

        });

    }
     */



    private CarResponse convertToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setModelYear(car.getModelYear());
        carResponse.setModelName(car.getModelName());
        carResponse.setDailyPrice(car.getDailyPrice());
        carResponse.setColor(car.getColor());
        carResponse.setStatus(car.getStatus());
        carResponse.setCarSupplierName(carResponse.getCarSupplierName());
        carResponse.setBrandName(carResponse.getBrandName());

        return carResponse;
    }
    public  boolean isReservable(LocalDate rentalDate, LocalDate returnDate, String startCarSupplier, String finishCarSupplier, int  carId) {


        Rental rental1=null;
        Rental rental2=null;
        boolean isSuitable=false;

        Car car=this.getOrginalCarById(carId);

        List<Rental> rentals=car.getRentals();
        if(rentals.size()==0 &&
                car.getCarSupplier().getName().equals(startCarSupplier))return true;
        if(rentals.size()==0) return false;

        Collections.sort(rentals, Comparator.comparing(Rental::getRentalDate));

        if (rentals.size()==1 ){

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


