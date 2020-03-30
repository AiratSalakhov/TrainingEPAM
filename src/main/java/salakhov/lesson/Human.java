package salakhov.lesson;

import java.lang.reflect.Field;
import java.util.Objects;

@Entity
public class Human {
    private static final int DEFAULT_AGE = 36;
    private static final String DEFAULT_NAME = "Default Name";

    @Value(age = 35)
    private int age;
    @Value(name = "Petya")
    private String name;

    public Human() {
        if (this.getClass().isAnnotationPresent(Entity.class)) {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    System.out.println("annotation value present for field " + field.getName());
                    Value annotation = field.getAnnotation(Value.class);
                    if (field.getName().equalsIgnoreCase("name")) {
                        try {
                            field.setAccessible(true);
                            if (annotation.name().isEmpty()) {
                                field.set(this, DEFAULT_NAME);
                            } else {
                                field.set(this, annotation.name());
                            }
                        } catch (IllegalAccessException e) {
                            System.out.println("setting field name - " + e);
                        }
                    } else if (field.getName().equalsIgnoreCase("age")) {
                        try {
                            field.setAccessible(true);
                            if (annotation.age() != 0) {
                                field.set(this, annotation.age());
                            } else {
                                field.set(this, DEFAULT_AGE);
                            }
                        } catch (IllegalAccessException e) {
                            System.out.println("setting field age - " + e);
                        }

                    }
                }
            }
        }
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
