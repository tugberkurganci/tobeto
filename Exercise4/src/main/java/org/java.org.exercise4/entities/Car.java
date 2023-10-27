package main.java.org.java.org.exercise4.entities;

public class Car extends AbstractVehicle {


    private int id;
    private int speed;
    private int distance;
    private boolean isWork;


    public Car(String brand, String model, int year) {
        super(brand, model, year);
    }

    public Car(String brand, String model, int year, int speed, int distance, boolean isWork) {
        super(brand, model, year);
        this.speed = speed;
        this.distance = distance;
        this.isWork = isWork;
    }



    @Override
    public void turnOn() {
        isWork=true;
    }

    @Override
    public void turnOff() {
        isWork=false;
    }

    @Override
    public void go(int newDistance) {
        distance += newDistance;

    }

    @Override
    public void stop() {
        this.speed = 0;
    }

    @Override
    public void accelerate(int newSpeed) {
            speed = newSpeed;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }



}
