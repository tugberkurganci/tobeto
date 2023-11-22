package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.repositories.CarSupplierRepository;
import com.tobeto.a.spring.intro.services.abstracts.CarSupplierService;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarSupplierResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarSupplierManager implements CarSupplierService {

    private final CarSupplierRepository carSupplierRepository;


    public CarSupplierManager(CarSupplierRepository carSupplierRepository) {
        this.carSupplierRepository = carSupplierRepository;
    }

    @Override
    public List<CarSupplierResponse> getAllCarSuppliers() {
        List<CarSupplier> carSupplierList = carSupplierRepository.findAll();
        return carSupplierList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarSupplierResponse getCarSupplierById(int id) {
        CarSupplier carSupplier = carSupplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarSupplier not found with id: " + id));
        return convertToResponse(carSupplier);
    }

    @Override
    public void createCarSupplier(CreateCarSupplierRequest carSupplierRequest) {
        CarSupplier carSupplier = new CarSupplier();
        carSupplier.setName(carSupplierRequest.getName());
        carSupplier.setLocation(carSupplierRequest.getLocation());

        carSupplierRepository.save(carSupplier);
    }

    @Override
    public void updateCarSupplier(UpdateCarSupplierRequest updatedCarSupplierRequest) {
        Optional<CarSupplier> existingCarSupplier = carSupplierRepository.findById(updatedCarSupplierRequest.getId());

        if (existingCarSupplier.isPresent()) {
            CarSupplier carSupplier = existingCarSupplier.get();
            carSupplier.setName(updatedCarSupplierRequest.getName());
            carSupplier.setLocation(updatedCarSupplierRequest.getLocation());

            carSupplierRepository.save(carSupplier);
        } else {
            throw new RuntimeException("There is no CarSupplier");
        }
    }

    @Override
    public void deleteCarSupplier(int id) {
        if (carSupplierRepository.existsById(id)) {
            carSupplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("CarSupplier not found with id: " + id);
        }
    }

    private CarSupplierResponse convertToResponse(CarSupplier carSupplier) {
        CarSupplierResponse carSupplierResponse = new CarSupplierResponse();
        carSupplierResponse.setName(carSupplier.getName());
        carSupplierResponse.setLocation(carSupplier.getLocation());

        return carSupplierResponse;

    }

    public CarSupplier getOriginalCarSupplierById(int id) {
        return carSupplierRepository.findById(id).orElseThrow();

    }
}
