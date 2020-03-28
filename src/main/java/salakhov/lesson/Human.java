package salakhov.lesson;

import java.util.Objects;

@Entity(name = "marked")
public class Human {
    private int age;
    private String name;

    public Human() {
        // вот здесь заполним по аннотациям
    }

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age &&
                name.equals(human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
