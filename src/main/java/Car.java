import java.util.*;
import java.util.concurrent.*;


public class Car extends Thread implements Runnable, Comparable <Car> {

    public String getCarName() {
        return carName;
    }

    private String carName;
    private double speed;
    private double distance;


    public Car(String name) {
        this.carName = name;
        this.distance = 0;
        this.speed = 0;
    }

    double setSpeed() {
        speed = ThreadLocalRandom.current().nextInt(100, 200 + 1);
        return speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            calculateDistance();

            synchronized (Race.intermediateResult) {
                Race.intermediateResult.add(this);
                if (Race.intermediateResult.size() == 10) {
                    Collections.sort(Race.intermediateResult);
                    displayTopCars(Race.intermediateResult);
                    Race.intermediateResult.clear();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getDistance() {
        return distance;
    }


    public int compareTo(Car car) {
        if (this.getDistance() == car.getDistance()) {
            return 0;
        } else if (this.getDistance() > car.getDistance()) {
            return -1;
        } else {
            return 1;
        }
    }

    public static void displayTopCars(List<Car> listOfCars) {
        Collections.sort(listOfCars);
        System.out.println("\nTOP 3 CARS:\n\t3d: " + listOfCars.get(2).getCarName() + " with distance " + listOfCars.get(2).getDistance() + " and the speed " + listOfCars.get(2).getSpeed() +
                "\n\t2d: " + listOfCars.get(1).getCarName() + " with the distance " + listOfCars.get(1).getDistance() + " and the speed " + listOfCars.get(1).getSpeed() +
                "\n\t1st: " + listOfCars.get(0).getCarName() + " with the distance " + listOfCars.get(0).getDistance() + " and the speed " + listOfCars.get(0).getSpeed());
    }

    public void calculateDistance() {

        double speedInSeconds = setSpeed() / 3600;
        distance += speedInSeconds;
    }
}


