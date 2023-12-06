package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {


    @Query("SELECT new com.tobeto.a.spring.intro.services.models.responses.CarResponse(c.modelYear,c.modelName,c.dailyPrice,c.brand.name,c.carSupplier.name ) FROM Car c " +
            "WHERE (COALESCE(:modelYear1, c.modelYear) IS NULL OR c.modelYear >= COALESCE(:modelYear1, c.modelYear)) " +
            "AND (COALESCE(:modelYear2, c.modelYear) IS NULL OR c.modelYear <= COALESCE(:modelYear2, c.modelYear)) " +
            "AND (LOWER(c.modelName) = COALESCE(LOWER(:modelName), LOWER(c.modelName)) OR COALESCE(:modelName, '') IS NULL) " +
            "AND (COALESCE(:dailyPrice1, c.dailyPrice) IS NULL OR c.dailyPrice >= COALESCE(:dailyPrice1, c.dailyPrice)) " +
            "AND (COALESCE(:dailyPrice2, c.dailyPrice) IS NULL OR c.dailyPrice <= COALESCE(:dailyPrice2, c.dailyPrice)) " +
            "AND (LOWER(c.color) = COALESCE(LOWER(:color), LOWER(c.color)) OR COALESCE(:color, '') IS NULL) " +
            "AND (LOWER(c.brand.name) = COALESCE(LOWER(:brandName), LOWER(c.brand.name)) OR COALESCE(:brandName, '') IS NULL) " +
            "AND (LOWER(c.status) = COALESCE(LOWER(:status), LOWER(c.status)) OR COALESCE(:status, '') IS NULL)")
    List<CarResponse> findByDynamicFilters(
            @Param("modelYear1") Integer modelYear1,
            @Param("modelYear2") Integer modelYear2,
            @Param("modelName") String modelName,
            @Param("dailyPrice1") Integer dailyPrice1,
            @Param("dailyPrice2") Integer dailyPrice2,
            @Param("color") String color,
            @Param("brandName") String brandName,
            @Param("status") String status
    );

    @Query("select new com.tobeto.a.spring.intro.services.models.responses.CarResponse(c.modelYear,c.modelName,c.dailyPrice,c.brand.name,c.carSupplier.name ) from Car  c " +
            "inner join Rental r on r.car.id=c.id "+
            "group by r.car.id,c.modelYear,c.modelName,c.dailyPrice,c.brand.name,c.carSupplier.name " +
            "order by count(*) desc")
    List<CarResponse> sortedWhichCarRentedMore();



}