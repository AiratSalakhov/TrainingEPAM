package main.java.Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

public class DataBase implements InterfaceBD<Human> {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataBase.class.getName());

    // делаем без проверок на заполненность массива
    private Human[] database = new Human[10];

    //получение одной сущности
    public Human getOne(int num){
        System.out.println("получаем сущность номер " + num + " " + database[num]);
        log.info("получаем сущность номер " + num + " " + database[num] + " ===> log (FILE)");
        return database[num];
    }

    //получение всех сущностей
    public Human[] getAll(){
        System.out.println("получаем все сущности в массиве");
        return database;
    }

    //сохранение одной сущности
    public void setOne(Human t, int num){
        database[num] = t;
        System.out.println("сохраняем сущность номер " + num + " " + t);
        log.info("сохраняем сущность номер " + num + " " + t + " ===> log (FILE)");
    }

    //сохранение списка сущностей
    public void setAll(Human[] t){
        System.out.println("сохраняем все сущности в массиве");
        database = t;
    }
}
