package salakhov.lesson;

import salakhov.lesson.abstractFactory.AbsFactoryCar;
import salakhov.lesson.abstractFactory.AbsFactoryCarImpl;
import salakhov.lesson.abstractFactory.AbsFactoryCarOffRoad;
import salakhov.lesson.abstractFactory.AbsLada;
import salakhov.lesson.factory.*;

public class Main {
    public static void main(String[] args) {
        FactoryCar factoryCar = new FactoryCar();
        Car car1 = factoryCar.getCar("Lada");
        Car car2 = factoryCar.getCar("Jeep");
        car1.getInfo();
        car2.getInfo();

        boolean needOffRoad = true;
        AbsFactoryCar absFactoryCar = null;
        if (needOffRoad) {
            absFactoryCar = new AbsFactoryCarOffRoad();
        } else {
            absFactoryCar = new AbsFactoryCarImpl();
        }
        AbsLada lada = absFactoryCar.getLada();
        lada.getInfo();

        AdapterJeep adapterJeep = new AdapterJeep(new Lada());
        System.out.println(adapterJeep.getLadaPrice());

        CompositeCars compositeCars = new CompositeCars();
        compositeCars.add(new Lada());
        compositeCars.add(new Jeep());
        System.out.println(compositeCars.startEngine());

        TmplMetodCarImpl tmplMetodCar = new TmplMetodCarImpl();
        tmplMetodCar
    }
}
