package salakhov.lesson;

import java.util.Objects;

public class Animal {
    private boolean vegetarian;
    private int age;
    private String nickName;

    public Animal(boolean vegetarian, int age, String nickName) {
        this.vegetarian = vegetarian;
        this.age = age;
        this.nickName = nickName;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "vegetarian=" + vegetarian +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return vegetarian == animal.vegetarian &&
                age == animal.age &&
                Objects.equals(nickName, animal.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegetarian, age, nickName);
    }
}
