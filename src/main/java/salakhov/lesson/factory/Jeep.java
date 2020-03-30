package salakhov.lesson.factory;

public class Jeep implements Car {
    public void getInfo() {
        System.out.println("This is Jeep!");
    }

    public int getJeepPrice() {
        return 300;
    }

    public String startEngine() {
        return "Jeep engine started!";
    }

    public void repairCar() {
        MediatorCar.repairCar(this);
    }
}
