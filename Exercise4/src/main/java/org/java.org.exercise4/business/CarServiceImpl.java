package main.java.org.java.org.exercise4.business;

import main.java.org.java.org.exercise4.dataAccess.CarDao;
import main.java.org.java.org.exercise4.entities.Car;

import java.util.List;

public class CarServiceImpl implements CarService{
    private CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void stopTheCar(int carId) {
        Car car=carDao.getCarById(carId);
        if (!car.isWork())return;else {
            car.stop();
            car.turnOff();
        }
    }

    @Override
    public void setDistanceAndSpeed(int carId,int distance, int speed) {
        Car car=carDao.getCarById(carId);
        car.turnOn();
        car.go(distance);
        car.accelerate(speed);

    }

    @Override
    public int calculateArrivalTime(int carId) {

        Car car=carDao.getCarById(carId);
        if (car.isWork()){
            System.out.println(car.getDistance()/car.getSpeed());
        return  car.getDistance()/car.getSpeed();
        }else return 0;

    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public Car getCarById(int id) {
        return carDao.getCarById(id);
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    @Override
    public void deleteCar(int id) {
        carDao.deleteCar(id);
    }
}
