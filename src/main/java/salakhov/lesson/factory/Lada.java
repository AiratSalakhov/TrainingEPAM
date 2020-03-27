package salakhov.lesson.factory;

public class Lada implements Car {
    public void getInfo() {
        System.out.println("This is Lada!");
    }

    public int getLadaPrice() {
        return 100;
    }

    public String startEngine() {
        return "Lada engine started!";
    }
}
