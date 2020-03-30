package salakhov.lesson.factory;

abstract public class TmplMetodCar {
    abstract void startEngine();

    abstract void stopEngine();

    public final void checkEngine() {
        System.out.println("Checking engine...");
        startEngine();
        stopEngine();
    }
}
