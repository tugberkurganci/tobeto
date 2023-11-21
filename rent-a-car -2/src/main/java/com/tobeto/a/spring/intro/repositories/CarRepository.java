package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.Brand;
import com.tobeto.a.spring.intro.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
