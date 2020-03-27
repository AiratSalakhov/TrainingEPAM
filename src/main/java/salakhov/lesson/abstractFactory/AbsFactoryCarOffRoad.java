package salakhov.lesson.abstractFactory;

public class AbsFactoryCarOffRoad implements AbsFactoryCar {
    public AbsLada getLada() {
        return new AbsLadaOffRoad();
    }

    public AbsJeep getJeep() {
        return new AbsJeepOffRoad();
    }
}
