package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.repositories.BrandRepository;
import com.tobeto.a.spring.intro.services.abstracts.BrandService;
import com.tobeto.a.spring.intro.services.models.requests.CreateBrandRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateBrandRequest;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;


    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<Brand> brandList = brandRepository.findAll();
        return brandList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponse getBrandById(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        return convertToResponse(brand);
    }

    @Override
    public void createBrand(CreateBrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(UpdateBrandRequest updatedBrandRequest) {
        Optional<Brand> existingBrand = brandRepository.findById(updatedBrandRequest.getId());

        if (existingBrand.isPresent()) {
            Brand brand = existingBrand.get();
            brand.setName(updatedBrandRequest.getName());
            brandRepository.save(brand);
        } else {
            throw new RuntimeException("There is no brand");
        }
    }

    @Override
    public void deleteBrand(int id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brand not found with id: " + id);
        }
    }

    @Override
    public Brand getOriginalBrandById(int brandId) {
        return brandRepository.findById(brandId).orElseThrow();
    }

    private BrandResponse convertToResponse(Brand brand) {
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setName(brand.getName());
        return brandResponse;
    }
}
