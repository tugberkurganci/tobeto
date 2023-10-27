package main.java.org.java.org.exercise4.entities;

public abstract class AbstractVehicle implements Vehicle{

    private final String brand;
    private final String model;
    private final int year;

     public AbstractVehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "AbstractVehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
