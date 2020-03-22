package Salakhov.Lesson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        String commandToDo;
        int lineNum;
        String fileName = "";
        String stringToAdd;
        String consoleLine;
        Filter filter = new Filter();
        while (true) {
            System.out.println("============================================================================");
            System.out.println("Введите команду (exit|add|delete|print [string num] FILENAME <\"String value\">)...");
            consoleLine = consoleScanner.nextLine();
            if (consoleLine.isEmpty()) continue;
            Scanner scanner = new Scanner(consoleLine);
            commandToDo = scanner.next().toLowerCase();
            if (commandToDo.equals("exit")) break;
            lineNum = 0;
            if (scanner.hasNextInt()) {
                lineNum = scanner.nextInt();
            }
            if (scanner.hasNext()) {
                fileName = scanner.next();
            }
            stringToAdd = "";
            if (scanner.hasNext()) {
                stringToAdd = scanner.nextLine();
                stringToAdd = stringToAdd.trim();
            }
            if (filter.parseCommand(commandToDo, lineNum, fileName, stringToAdd)) {
                System.out.println("Команда выполнена!");
            } else {
                System.out.println("Ошибка! Попробуйте еще раз...");
            }
        }
    }
}
