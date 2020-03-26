package salakhov.lesson.abstractFactory;

public class FactoryCar implements InterfaceFactoryCar {
    public Lada getCar() {
        return new Lada();
    }
    public Jeep getCar() {
        return new Jeep();
    }
}
