package Salakhov.Lesson.Handlers;

import Salakhov.Lesson.FileProcessor;

public class HandlerPrint implements Salakhov.Lesson.Handlers.Handlers {
    FileProcessor fileProcessor = new FileProcessor();

    @Override
    public boolean execute(Integer lineNum, String fileName, String stringToAdd) {
        if (fileName.isEmpty()) {
            System.out.println("Вы не ввели имя файла для обработки. Попробуйте еще раз...");
            return false;
        }
        if (!stringToAdd.isEmpty()) {
            System.out.println("Лишний параметр - добавляемая строка!");
            return false;
        }
        fileProcessor.clearBuffer();
        if (!fileProcessor.openReader(fileName)) {
            return false;
        }
        if (!fileProcessor.read()) {
            return false;
        }
        if (!fileProcessor.closeReader()) {
            return false;
        }
        if (lineNum == 0) {
            for (String line : fileProcessor.getLinkedList()) {
                System.out.println(line);
            }
        } else if (lineNum > fileProcessor.getLinkedList().size()) {
            System.out.println("В файле нет строки с номером " + lineNum + ", всего строк: " + fileProcessor.getLinkedList().size());
        } else {
            System.out.println(fileProcessor.getLinkedList().get(lineNum - 1));
        }
        fileProcessor.clearBuffer();
        return true;
    }
}
