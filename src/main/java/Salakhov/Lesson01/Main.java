package Salakhov.Lesson01;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s1 = "Some string for testing...";
        String s2 = "SOME string for Testing...";
        String s3 = "Some (aaa Sun) string for testing with symbols...  Some (aaa Sun) string for testing with symbols...";
        System.out.println("Hello World!");
        System.out.println("s1 length = " + s1.length());
        System.out.println("s1 equals s2 ? " + s1.equalsIgnoreCase(s2));
        s3 = s3.intern();
        char[] arr1 = s1.toCharArray();
        System.out.println(Arrays.toString(arr1));
        byte[] arr2 = s1.getBytes();
        System.out.println(Arrays.toString(arr2));
        s1 = s1.toUpperCase();
        System.out.println("Upper case s1 = " + s1);
        System.out.println("index of first a = " + s3.indexOf('a'));
        System.out.println("index of last a = " + s3.lastIndexOf('a'));
        if (s3.contains("Sun")) {
            System.out.println("Contains Sun? - true");
        } else {
            System.out.println("Contains Sun? - false");
        }
        if (s3.endsWith("Oracle")) {
            System.out.println("Ends with Oracle? - true");
        } else {
            System.out.println("Ends with Oracle? - false");
        }
        if (s3.startsWith("Java")) {
            System.out.println("Starts with Java? - true");
        } else {
            System.out.println("Starts with Java? - false");
        }
        System.out.println(s3.replace('a', 'o'));
        System.out.println(s3.subSequence(43, 89));
        System.out.println(Arrays.toString(s3.split(" ")));
        System.out.println(new StringBuffer(s3).reverse());
    }
}
