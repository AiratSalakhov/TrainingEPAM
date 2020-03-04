package Lesson02;

import java.lang.reflect.Array;

public interface InterfaceBD<T> {
    //получение одной сущности
    public T getOne(int num);

    //получение всех сущностей
    public T[] getAll();

    //сохранение одной сущности
    public void setOne(T t, int num);

    //сохранение списка сущностей
    public void setAll(T[] t);
}
