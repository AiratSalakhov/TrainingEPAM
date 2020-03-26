package salakhov.lesson;

import salakhov.lesson.factory.Car;
import salakhov.lesson.factory.FactoryCar;

public class Main {
    public static void main(String[] args) {
        FactoryCar factoryCar = new FactoryCar();
        Car car1 = factoryCar.getCar("Lada");
        Car car2 = factoryCar.getCar("Jeep");
        car1.getInfo();
        car2.getInfo();
    }
}
