package Salakhov.Lesson.Task2;

public class Main {
    public static void main(String[] args) {
        int counter = 0;

       // Task 2 Написать код, который будет выбрасывать java.lang.StackOverflowError
        recurced(counter);
    }

    private static void recurced(int counter) {
        counter++;
        System.out.println(counter);
        recurced(counter);

    }

}
