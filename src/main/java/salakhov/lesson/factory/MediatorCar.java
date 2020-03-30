package salakhov.lesson.factory;

public class MediatorCar {
    public static void repairCar(Car car) {
        System.out.println("Repairirng car...");
        car.getInfo();
    }
}
