package main.java.Salakhov.Lesson02;

public class DataBase implements InterfaceBD<Human> {

    // делаем без проверок на заполненность массива
    private Human[] database = new Human[10];

    //получение одной сущности
    public Human getOne(int num){
        System.out.println("получаем сущность номер " + num + " " + database[num]);
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
    }

    //сохранение списка сущностей
    public void setAll(Human[] t){
        System.out.println("сохраняем все сущности в массиве");
        database = t;
    }


}
