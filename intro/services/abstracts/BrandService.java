package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.services.models.requests.CreateBrandRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateBrandRequest;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandResponse> getAllBrands();
    BrandResponse getBrandById(int id);
    void createBrand(CreateBrandRequest brandRequest);
    void updateBrand(UpdateBrandRequest updatedBrandRequest);
    void deleteBrand(int id);

    Brand getOriginalBrandById(int brandId);
}