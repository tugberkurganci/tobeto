package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.CarSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSupplierRepository extends JpaRepository<CarSupplier,Integer> {
}
