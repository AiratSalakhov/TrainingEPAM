import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Нужно закинуть туда простую программу с выводом Hello World
        System.out.println("Hello World!");

        String s1 = "Some string for testing...";
        String s2 = "SOME string for Testing...";

        //1. Получить длину строки
        System.out.println("s1 length = " + s1.length());

        //2. Сравнить 2 строки без учета регистра
        System.out.println("s1 equals s2 ? " + s1.equalsIgnoreCase(s2));

        //3. Создать новую строку с помощью конструктора и занести ее в пул литералов
        String s3 = new String("Some (aaa Sun) string for testing with symbols...  Some (aaa Sun) string for testing with symbols...");
        s3 = s3.intern();

        //4. Получить из строки массив символов
        char[] arr1 = s1.toCharArray();
        System.out.println(Arrays.toString(arr1));

        //5. Получить из строки массив байтов
        byte[] arr2 = s1.getBytes();
        System.out.println(Arrays.toString(arr2));

        //6. Привести строку к верхнему регистру
        s1 = s1.toUpperCase();
        System.out.println("Upper case s1 = " + s1);

        //7. Найти первую позицию символа "а" в строке
        System.out.println("index of first a = " + s3.indexOf('a'));

        //8. Найти последнюю позицию символа "а" в строке
        System.out.println("index of last a = " + s3.lastIndexOf ('a'));

        //9. Проверить содержит-ли строка слово "Sun"
        if (s3.contains("Sun")) {
            System.out.println("Contains Sun? true");
        }
        else {
            System.out.println("Contains Sun? false");
        }

        //10. Проверить оканчивается-ли строка на "Oracle"
        if (s3.endsWith("Oracle")) {
            System.out.println("Ends with Oracle? true");
        }
        else {
            System.out.println("Ends with Oracle? false");
        }

        //11. Проверить начинается-ли строка на "Java"
        if (s3.startsWith("Java")) {
            System.out.println("Starts with Java? true");
        }
        else {
            System.out.println("Starts with Java? false");
        }

        //12. Заменить все символы "а" в строке на символ "о"
        System.out.println(s3.replace('a', 'o'));

        //13. Получить подстроку с 44 символа по 90 символ
        System.out.println(s3.subSequence(43, 89));

        //14. Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)
        System.out.println(Arrays.toString(s3.split(" ")));

        //15. Поменять последовательность символов в строке на обратную.
        System.out.println(new StringBuffer(s3).reverse());
    }
}
