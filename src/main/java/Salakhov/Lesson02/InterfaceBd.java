package Salakhov.Lesson02;

public interface InterfaceBd<T> {
    public T getOne(int num);

    public T[] getAll();

    public void setOne(T t, int num);

    public void setAll(T[] t);
}
