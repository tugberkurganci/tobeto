package main.java.org.java.org.exercise4.business;

import main.java.org.java.org.exercise4.entities.Car;

import java.util.List;

public interface CarService {


    void stopTheCar(int carId);
    void setDistanceAndSpeed(int carId,int distance,int speed);
    int calculateArrivalTime(int carId);

    List<Car> getAllCars();


    Car getCarById(int id);


    void addCar(Car car);


    void updateCar(Car car);


    void deleteCar(int id);
}
