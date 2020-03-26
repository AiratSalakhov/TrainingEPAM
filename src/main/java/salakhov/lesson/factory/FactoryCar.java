package salakhov.lesson.factory;

public class FactoryCar {
    public Car getCar(String carType){
        Car car = null;
        if (carType=="Lada"){
            car = new Lada();
        } else {
            car = new Jeep();
        }
        return car;
    }
}
