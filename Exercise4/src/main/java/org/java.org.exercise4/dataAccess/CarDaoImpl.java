package main.java.org.java.org.exercise4.dataAccess;

import main.java.org.java.org.exercise4.entities.Car;

import java.util.ArrayList;
import java.util.List;
public class CarDaoImpl implements CarDao {

    private List<Car> carList;

    public CarDaoImpl() {
        carList = new ArrayList<>();
    }

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Car getCarById(int id) {
        for (Car car : carList) {

            if (car.getId() == id) {


                return car;
            }
        }
        return null;
    }

    @Override
    public void addCar(Car car) {
        car.setId(carList.size());
        carList.add(car);

    }

    @Override
    public void updateCar(Car car) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getId() == car.getId()) {
                carList.set(i, car);
                return;
            }
        }

    }

    @Override
    public void deleteCar(int id) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getId() == id) {
                carList.remove(i);
                return;
            }
        }

    }
}

