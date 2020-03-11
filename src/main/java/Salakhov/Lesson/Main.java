package main.java.Salakhov.Lesson;

import java.util.Scanner;
import main.java.Salakhov.Lesson.Filter;

public class Main {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);
        String commandToDo = "";
        Integer lineNum;
        String fileName;
        String stringToAdd;
        String consoleLine;

        Filter filter = new Filter();

        while (true) {
            System.out.println("============================================================================");
            System.out.println("Введите команду (add|delete|print [string num] FILENAME <\"String value\">)...");
            consoleLine = consoleScanner.nextLine();
            Scanner scanner = new Scanner(consoleLine);

            commandToDo = scanner.next().toLowerCase();
            if (commandToDo.equalsIgnoreCase("exit")) break;
            lineNum = 0;
            if (scanner.hasNextInt()) {
                lineNum = scanner.nextInt();
                System.out.println("lineNum=" + lineNum);
            }
            if (scanner.hasNext()) {
                fileName = scanner.next();
                System.out.println("fileName=" + fileName);
            } else {
                System.out.println("Вы не ввели имя файла для обработки. Попробуйте еще раз...");
                continue;
            }
            // остался хвост строки - это либо строка для добавления, либо мусор
            stringToAdd = "";
            if (scanner.hasNext()) {
                stringToAdd = scanner.nextLine();
                System.out.println("stringToAdd=" + stringToAdd);
            }
            if (filter.parseCommand(commandToDo, lineNum, fileName, stringToAdd)) {
                System.out.println("Команда выполнена!");
            } else {
                System.out.println("Ошибка! Попробуйте еще раз...");
            }
        }
    }
}
