package Salakhov.Lesson;

import Salakhov.Lesson.DynamicLoading.TestClass;

public class Main {
    public static void main(String[] args) {
        // Task 3 Написать свой загрузчик класса, который будет подгружать классы из директории вне вашего проекта.
        // здесь проверим работу класса без динамической загрузки...

        TestClass testClass = new TestClass();
        System.out.println(testClass.toString());
    }
}
