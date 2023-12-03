package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.repositories.BrandRepository;
import com.tobeto.a.spring.intro.services.abstracts.BrandService;
import com.tobeto.a.spring.intro.services.models.requests.CreateBrandRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdateBrandRequest;
import com.tobeto.a.spring.intro.services.models.responses.BrandResponse;
import com.tobeto.a.spring.intro.services.models.responses.CarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;


    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public BrandResponse getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    public void createBrand(@RequestBody List<CreateBrandRequest> brandRequest) {
         brandService.createBrand(brandRequest);

    }

    @PutMapping
    public void updateBrand(@RequestBody UpdateBrandRequest updatedBrandRequest) {
        brandService.updateBrand(updatedBrandRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
    }

    @GetMapping("/findCarByBrandName")
    public List<CarResponse> findCarByBrandName(@RequestParam String brandName) {
        return brandService.findCarByBrandName(brandName);
    }

    @GetMapping("/sortedBrands")
    public List<BrandResponse> getSortedBrandList(){
        return  brandService.getSortedBrandList();
    }



}

