package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.services.models.requests.CreateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateCarSupplierRequest;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarSupplierResponse;

import java.util.List;

public interface CarSupplierService {


    List<CarSupplierResponse> getAllCarSuppliers();

    CarSupplierResponse getCarSupplierById(int id);

    void createCarSupplier(List<CreateCarSupplierRequest> carSupplierRequest);

    void updateCarSupplier(UpdateCarSupplierRequest updatedCarSupplierRequest);

    void deleteCarSupplier(int id);

    CarSupplier getOriginalCarSupplierById(int carSupplierId);

    List<CarSupplierResponse> findByLocation(String location);

    List<CarResponse> getCarSuppliersAndCarInfo(String carSupplierName);
}
