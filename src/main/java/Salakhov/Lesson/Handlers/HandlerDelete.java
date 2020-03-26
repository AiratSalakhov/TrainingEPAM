package Salakhov.Lesson.Handlers;

import Salakhov.Lesson.FileProcessor;

public class HandlerDelete implements Salakhov.Lesson.Handlers.Handlers {
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
        if (lineNum == 0 && fileProcessor.getLinkedList().size() > 0) {
            fileProcessor.getLinkedList().remove(fileProcessor.getLinkedList().size() - 1);
        } else if (lineNum > fileProcessor.getLinkedList().size()) {
            System.out.println("В файле нет строки с номером " + lineNum + ", всего строк: " + fileProcessor.getLinkedList().size());
            fileProcessor.clearBuffer();
            return true;
        } else {
            fileProcessor.getLinkedList().remove(lineNum - 1);
        }
        if (!fileProcessor.openWriter(fileName)) {
            return false;
        }
        if (!fileProcessor.write()) {
            return false;
        }
        return fileProcessor.closeWriter();
    }
}
