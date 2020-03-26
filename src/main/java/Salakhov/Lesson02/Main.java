package Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

import java.util.Date;

public class Main {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Service service = new Service();
        String[] names = {"Вася", "Петя", "Саша", "Гена", "Дима"};
        String[] cities = {"Тольятти", "Самара", "Москва", "Воронеж", "Казань"};
        String[] streets = {"Юбилейная", "Ленина", "Революционная", "Южная", "Северная"};
        String[] houses = {"31Е", "20", "38", "1", "135/6"};
        log.info("============= My message to log... ==> log (root CON)");

        for (int i = 0; i < 10; i++) {
            service.setOne(new HumanDto((int) (Math.random() * 10000),
                            names[(int) (Math.random() * 4)],
                            cities[(int) (Math.random() * 4)],
                            streets[(int) (Math.random() * 4)],
                            houses[(int) (Math.random() * 4)],
                            new Date(110 - (int) (Math.random() * 60),
                                    1 + (int) (Math.random() * 11),
                                    1 + (int) (Math.random() * 27))),
                    i);
        }
        System.out.println("---------------------");
        System.out.println("throw MyException...");

        try {
            getExcpetion();
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("отловили исключение в блоке catch{}");
        } finally {
            System.out.println("отловили исключение и выполняем блок finally{}");
        }

        service.getOne(8);
    }

    private static void getExcpetion() throws MyException {
        throw new MyException("Выкидываем наше исключение!");
    }
}
