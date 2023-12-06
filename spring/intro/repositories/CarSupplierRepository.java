package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarSupplierResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarSupplierRepository extends JpaRepository<CarSupplier,Integer> {
    List<CarSupplier> findByLocation(String location);

    @Query("select  new com.tobeto.a.spring.intro.services.models.responses.CarResponse(c.modelYear,c.modelName,c.dailyPrice,c.brand.name,c.carSupplier.name )  " +
            " from Car c  where c.carSupplier.name=:carSupplierName" )
    List<CarResponse> getCarSuppliersAndCarInfo(String carSupplierName);
}
