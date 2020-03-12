package main.java.Salakhov.Lesson02;

import java.util.Date;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

//@Slf4j

public class Main {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {

        log.info("============= My message to log... ==> log (root CON)");

        Service service = new Service();
        // заносим данные
        String[] names = {"Вася", "Петя", "Саша", "Гена", "Дима"};
        String[] cities = {"Тольятти", "Самара", "Москва", "Воронеж", "Казань"};
        String[] streets = {"Юбилейная", "Ленина", "Революционная", "Южная", "Северная"};
        String[] houses = {"31Е", "20", "38", "1", "135/6"};

        for(int i=0; i<10; i++) {
            service.setOne(new HumanDTO((int)(Math.random()*10000), names[(int)(Math.random()*4)],
                    cities[(int)(Math.random()*4)], streets[(int)(Math.random()*4)], houses[(int)(Math.random()*4)],
                    new Date(110-(int)(Math.random()*60), 1+(int)(Math.random()*11), 1+(int)(Math.random()*27))), i);
        }
        System.out.println("---------------------");
        System.out.println("throw MyException...");

        try {getExcpetion();}
        catch (MyException e) {e.printStackTrace(); System.out.println("отловили исключение в блоке catch{}");}
        finally {System.out.println("отловили исключение и выполняем блок finally{}");}

        // получаем данные
        service.getOne(8);
    }

    private static void getExcpetion() throws MyException {
        throw new MyException("Выкидываем наше исключение!");
    }
}
