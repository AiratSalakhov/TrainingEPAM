package Lesson02;

public class DataBase implements InterfaceBD<Human> {

    // делаем без проверок на заполненность массива
    private Human[] database = new Human[10];

    //получение одной сущности
    public Human getOne(int num){
        System.out.println("получаем одну сущность номер " + num + " " + database[num]);
        return database[num];
    }

    //получение всех сущностей
    public Human[] getAll(){
        return database;
    }

    //сохранение одной сущности
    public void setOne(Human t, int num){
        database[num] = t;
        System.out.println("сохраняем одну сущность номер " + num + " " + t);
    }

    //сохранение списка сущностей
    public void setAll(Human[] t){
        database = t;
    }


}
