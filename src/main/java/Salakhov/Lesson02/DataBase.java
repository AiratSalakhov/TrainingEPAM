package Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

public class DataBase implements InterfaceBd<Human> {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataBase.class.getName());
    private Human[] humans = new Human[10];

    public Human getOne(int num) {
        System.out.println("получаем сущность номер " + num + " " + humans[num]);
        log.info("получаем сущность номер " + num + " " + humans[num] + " ===> log (FILE)");
        return humans[num];
    }

    public Human[] getAll() {
        System.out.println("получаем все сущности в массиве");
        return humans;
    }

    public void setOne(Human human, int num) {
        humans[num] = human;
        System.out.println("сохраняем сущность номер " + num + " " + human);
        log.info("сохраняем сущность номер " + num + " " + human + " ===> log (FILE)");
    }

    public void setAll(Human[] humans) {
        System.out.println("сохраняем все сущности в массиве");
        this.humans = humans;
    }
}
