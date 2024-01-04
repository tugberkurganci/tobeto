package com.tobeto.pair3.repositories;

import com.tobeto.pair3.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);

    List<Car> findByModelId(int modelId);
}
