package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    @Query("Select new com.tobeto.a.spring.intro.services.models.responses.CarResponse(c.modelYear,c.modelName,c.dailyPrice,b.name,c.carSupplier.name ) " +
            "from  Brand b inner join Car c on c.brand.id=b.id where b.name= :brandName")
    List<CarResponse> findCarByBrandName(String brandName);


    @Query(" select new com.tobeto.a.spring.intro.services.models.responses.BrandResponse(b.name) from Brand b order by b.name" )
    List<BrandResponse> getSortedBrandList();
}
/*@Query("Select new com.tobeto.a.spring.intro.services.dtos.brand.responses.GetListBrandResponse(b.id, b.name) " +
        "FROM Brand b WHERE b.name= :name")
*/

/* @Query("Select new com.tobeto.a.spring.intro.services.dtos.brand.responses.GetListBrandResponse(b.id, b.name) " +
        "FROM Brand b WHERE b.name= :name")

 select c.model_name,b.name from brands b
        inner join cars  c on c.brand_id=b.id
        where b.name=bmw

 */