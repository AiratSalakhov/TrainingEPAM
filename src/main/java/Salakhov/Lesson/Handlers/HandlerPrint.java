package main.java.Salakhov.Lesson.Handlers;

import main.java.Salakhov.Lesson.FileReader;

public class HandlerPrint implements main.java.Salakhov.Lesson.Handlers.Handlers {

    FileReader fileReader = new FileReader();

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

        if (!fileReader.openReader(fileName)) {return false;}
        if (!fileReader.read()) {return false;}
        if (!fileReader.closeReader()) {return false;}
        if (lineNum == 0) {
            for (String line : fileReader.getBuffer()) {
                System.out.println(line);
            }
        } else if (lineNum > fileReader.getBuffer().size()) {
            System.out.println("В файле нет строки с номером " + lineNum + ", всего строк: " + fileReader.getBuffer().size());
        } else {
            System.out.println(fileReader.getBuffer().get(lineNum-1));
        }
        fileReader.clearBuffer();
        return true;
    }
}
