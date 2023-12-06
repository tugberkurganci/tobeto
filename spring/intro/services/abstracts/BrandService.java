package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.services.models.requests.CreateBrandRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateBrandRequest;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandResponse> getAllBrands();
    BrandResponse getBrandById(int id);
    void createBrand(List<CreateBrandRequest> brandRequest);

    void updateBrand(UpdateBrandRequest updatedBrandRequest);
    void deleteBrand(int id);

    Brand getOriginalBrandById(int brandId);

    List<CarResponse> findCarByBrandName(String brandName);

    List<BrandResponse> getSortedBrandList();

    void createBrandV2(CreateBrandRequest brandRequest);
}