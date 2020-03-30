package salakhov.lesson.abstractFactory;

public class AbsFactoryCarImpl implements AbsFactoryCar {
    public AbsLada getLada() {
        return new AbsLadaImpl();
    }

    public AbsJeep getJeep() {
        return new AbsJeepImpl();
    }
}
