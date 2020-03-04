package Lesson02;

import java.util.Date;

public class Main {
    public static void main(String[] args){
        Service service = new Service();
        service.setOne(new HumanDTO(1, "Вася", "Тольятти", "Юбилейная", "31Е", new Date(2000, 1, 1)), 1);
        service.setOne(new HumanDTO(2, "Вася2", "Тольятти2", "Юбилейная2", "31Е1", new Date(2000, 1, 2)), 2);
        service.setOne(new HumanDTO(3, "Вася3", "Тольятти3", "Юбилейная3", "31Е2", new Date(2000, 1, 3)), 3);
        service.setOne(new HumanDTO(4, "Вася4", "Тольятти4", "Юбилейная4", "31Е3", new Date(2000, 1, 4)), 4);
        service.setOne(new HumanDTO(5, "Вася5", "Тольятти5", "Юбилейная5", "31Е4", new Date(2000, 1, 5)), 5);
    }
}
