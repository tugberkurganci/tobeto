package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.repositories.BrandRepository;
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

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable int id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("brand not found with id: " + id));
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @PutMapping
    public Brand updateBrand( @RequestBody Brand updatedBrand) {
        Optional<Brand> existingBrand = brandRepository.findById(updatedBrand.getId());

        if (existingBrand.isPresent()) {
            Brand brand = existingBrand.get();
            brand.setName(updatedBrand.getName());

            return brandRepository.save(brand);
        } else {

            throw  new RuntimeException("there is no brand");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        }else throw new RuntimeException("brand doesn't found");

    }
}
