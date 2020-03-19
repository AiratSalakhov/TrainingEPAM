package Salakhov.Lesson.Task2;

public class Main {
    public static void main(String[] args) {

        // Task 2 Написать код, который будет выбрасывать java.lang.StackOverflowError

        int counter = 0;
        recurced(counter);
    }

    private static void recurced(int counter) {
        System.out.println(counter + 1);
        recurced(counter + 1);
    }
}
