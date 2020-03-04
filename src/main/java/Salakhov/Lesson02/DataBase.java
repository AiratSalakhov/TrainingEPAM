package Lesson02;

public class DataBase<T> implements InterfaceBD<T> {

    // делаем без проверок на заполненность массива
    private T[] database = new T[10];

    //получение одной сущности
    public T getOne(int num){
        System.out.println("получаем одну сущность номер " + num + " " + database[num]);
        return database[num];
    }

    //получение всех сущностей
    public T[] getAll(){
        return database;
    }

    //сохранение одной сущности
    public void setOne(T t, int num){
        database[num] = t;
        System.out.println("сохраняем одну сущность номер " + num + " " + t);
    }

    //сохранение списка сущностей
    public void setAll(T[] t){
        database = t;
    }


}
