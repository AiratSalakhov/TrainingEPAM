import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        String s1 = "Some string for testing...";
        String s2 = "SOME string for Testing...";

        System.out.println("s1 length = " + s1.length());
        System.out.println("s1 equals s2 ? " + s1.equalsIgnoreCase(s2));

        String s3 = new String("Some (aaa Sun) string for testing with symbols...");
        s3 = s3.intern();

        char[] arr1 = s1.toCharArray();
        System.out.println(Arrays.toString(arr1));
        byte[] arr2 = s1.getBytes();
        System.out.println(Arrays.toString(arr2));

        s1 = s1.toUpperCase();
        System.out.println("upper case s1 = " + s1);

        System.out.println("index of first a = " + s3.indexOf('a'));
        System.out.println("index of last a = " + s3.lastIndexOf ('a'));
        if (s3.indexOf("Sun")>=0) {
            System.out.println("Contains Sun ? " + "true");
        }
        else {
            System.out.println("Contains Sun ? " + "false");
        }


    }
}
