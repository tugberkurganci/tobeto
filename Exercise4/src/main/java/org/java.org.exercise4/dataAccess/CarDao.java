package main.java.org.java.org.exercise4.dataAccess;

import main.java.org.java.org.exercise4.entities.Car;

import java.util.List;

public interface CarDao {

    List<Car> getAllCars();


    Car getCarById(int id);

    void addCar(Car car);


    void updateCar(Car car);


    void deleteCar(int id);
}