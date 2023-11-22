package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.repositories.CarRepository;
import com.tobeto.a.spring.intro.services.abstracts.BrandService;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.abstracts.CarSupplierService;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarRequest;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public void createCar(CreateCarRequest carRequest) {
        Car car = new Car();
        car.setModelYear(carRequest.getModelYear());
        car.setModelName(carRequest.getModelName());
        car.setDailyPrice(carRequest.getDailyPrice());
        car.setColor(carRequest.getColor());
        car.setStatus(carRequest.getStatus());
        CarSupplier carSupplier=supplierService.getOriginalCarSupplierById(carRequest.getCarSupplierId());
        car.setCarSupplier(carSupplier);
        Brand brand=brandService.getOriginalBrandById(carRequest.getBrandId());
        car.setBrand(brand);

        carRepository.save(car);
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
}


