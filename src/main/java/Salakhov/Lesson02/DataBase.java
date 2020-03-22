package Salakhov.Lesson02;

public class DataBase implements InterfaceBd<Human> {
    private Human[] database = new Human[10];

    public Human getOne(int num) {
        System.out.println("получаем сущность номер " + num + " " + database[num]);
        return database[num];
    }

    public Human[] getAll() {
        System.out.println("получаем все сущности в массиве");
        return database;
    }

    public void setOne(Human t, int num) {
        database[num] = t;
        System.out.println("сохраняем сущность номер " + num + " " + t);
    }

    public void setAll(Human[] t) {
        System.out.println("сохраняем все сущности в массиве");
        database = t;
    }
}
