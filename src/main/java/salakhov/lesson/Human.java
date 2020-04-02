package salakhov.lesson;

import java.util.Objects;

@Entity
public class Human {
    @Value(path = "src/main/resources/annotation.txt")
    private int age;
    @Value(path = "src/main/resources/annotation.txt")
    private String name;

    public Human() {
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
