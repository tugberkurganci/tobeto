package main.java.org.java.org.exercise4;


import main.java.org.example3.business.ProductService;
import main.java.org.example3.entities.Product;
import main.java.org.example3.logging.DatabaseLogger;
import main.java.org.example3.logging.FileLogger;
import main.java.org.example3.logging.MailLogger;
import main.java.org.java.org.exercise4.business.CarService;
import main.java.org.java.org.exercise4.business.CarServiceImpl;
import main.java.org.java.org.exercise4.dataAccess.CarDao;
import main.java.org.java.org.exercise4.dataAccess.CarDaoImpl;
import main.java.org.java.org.exercise4.entities.Car;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        CarServiceImpl carService=new CarServiceImpl(new CarDaoImpl());



        // Yeni arabalar oluşturun
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Honda", "Civic", 2021);
        Car car3 = new Car("Ford", "Focus", 2020);

        // Arabaları ekleyin
        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);

        // Tüm arabaları listeleme
        List<Car> allCars = carService.getAllCars();
        for (Car car : allCars) {
            System.out.println(car.toString());
        }

        // Belirli bir araba bilgisini al
        int carIdToRetrieve = 1; // Örnek bir araba ID'si
        Car retrievedCar = carService.getCarById(carIdToRetrieve);
        if (retrievedCar != null) {
            System.out.println("Retrieved Car: " + retrievedCar);
        } else {
            System.out.println("Car not found with ID: " + carIdToRetrieve);
        }
        // Araba bilgisini sil
        int carIdToDelete = 3; // Örnek bir araba ID'si
        carService.deleteCar(carIdToDelete);

        // Araba fonksiyonları çalıştırma ve güncelleme

        carService.setDistanceAndSpeed(1,100,50);
        carService.calculateArrivalTime(1);
        carService.stopTheCar(1);
    }
}