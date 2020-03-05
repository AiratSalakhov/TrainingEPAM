package main.java.Salakhov.Lesson02;

import java.util.Date;

public class Main {
    public static void main(String[] args){
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
        // получаем данные
        service.getOne(8);
    }
}
