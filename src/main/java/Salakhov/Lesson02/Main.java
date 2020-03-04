package Lesson02;

import java.util.Date;

public class Main {
    public static void main(String[] args){
        Service service = new Service();
        // заносим данные
        for(int i=0; i<10; i++) {
            service.setOne(new HumanDTO(), i);
        }
        System.out.println("---------------------");
        // получаем данные
        service.getOne(8);
    }
}
