package Salakhov.Lesson.Task1;

public class Main {
    public static void main(String[] args) {
        String s1 = new String();
        String s2 = new String();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000; j++) {
                s1 = s1 + "0123456789";
            }
            s2 = s2 + s1;
            System.out.println(s2.length());
        }
    }
}
