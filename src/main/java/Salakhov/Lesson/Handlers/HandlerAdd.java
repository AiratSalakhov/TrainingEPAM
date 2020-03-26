package Salakhov.Lesson.Handlers;

import Salakhov.Lesson.FileProcessor;

public class HandlerAdd implements Salakhov.Lesson.Handlers.Handlers {
    FileProcessor fileProcessor = new FileProcessor();

    @Override
    public boolean execute(Integer lineNum, String fileName, String stringToAdd) {
        if (fileName.isEmpty()) {
            System.out.println("Вы не ввели имя файла для обработки. Попробуйте еще раз...");
            return false;
        }
        if (stringToAdd.isEmpty() || !stringToAdd.startsWith("\"") || !stringToAdd.endsWith("\"")) {
            System.out.println("Неправильный формат добавляемой строки");
            return false;
        }
        stringToAdd = stringToAdd.substring(1, stringToAdd.length() - 1);
        fileProcessor.clearBuffer();
        if (fileProcessor.openReaderSilent(fileName)) {
            if (!fileProcessor.read()) {
                return false;
            }
            if (!fileProcessor.closeReader()) {
                return false;
            }
        }
        if (lineNum == 0 || (lineNum - fileProcessor.getLinkedList().size()) == 1) {
            fileProcessor.getLinkedList().add(stringToAdd);
        } else if (lineNum > fileProcessor.getLinkedList().size()) {
            int emptyLinesToAdd = lineNum - fileProcessor.getLinkedList().size();
            for (int i = 1; i < emptyLinesToAdd; i++) {
                fileProcessor.getLinkedList().add("");
            }
            fileProcessor.getLinkedList().add(stringToAdd);
        } else {
            fileProcessor.getLinkedList().add(lineNum - 1, stringToAdd);
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
