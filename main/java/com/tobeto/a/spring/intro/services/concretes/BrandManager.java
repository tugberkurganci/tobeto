package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.repositories.BrandRepository;
import com.tobeto.a.spring.intro.services.abstracts.BrandService;
import com.tobeto.a.spring.intro.services.abstracts.CarService;
import com.tobeto.a.spring.intro.services.models.requests.CreateBrandRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateBrandRequest;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final CarService carService;


    public BrandManager(BrandRepository brandRepository, @Lazy CarService carService) {
        this.brandRepository = brandRepository;
        this.carService = carService;
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
    public void createBrand(List<CreateBrandRequest> brandRequest) {

        brandRequest.stream().forEach(createBrandRequest ->{Brand brand = new Brand();
            brand.setName(createBrandRequest.getName());
            brandRepository.save(brand);} );

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

    @Override
    public List<CarResponse> findCarByBrandName(String brandName) {

        List<Brand> brandList=brandRepository.findAll();

        Optional<List<CarResponse>> carResponsesOptional = brandList.stream()
                .filter(brand -> brand.getName().equals(brandName))
                .findFirst()
                .map(brand -> brand.getCars().stream()
                        .map(CarResponse::new)
                        .collect(Collectors.toList()));
        if (carResponsesOptional.isPresent())return  carResponsesOptional.get();

        return brandRepository.findCarByBrandName(brandName);
    }

    @Override
    public List<BrandResponse> getSortedBrandList() {

        //List<Brand> brandList=brandRepository.findAll();
        //brandList.sort(Comparator.comparing(Brand::getName));



        return brandRepository.getSortedBrandList();
    }


    private BrandResponse convertToResponse(Brand brand) {
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setName(brand.getName());
        return brandResponse;
    }
}
