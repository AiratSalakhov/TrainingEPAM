package main.java.Salakhov.Lesson.Handlers;

import main.java.Salakhov.Lesson.FileReader;

public class HandlerDelete implements main.java.Salakhov.Lesson.Handlers.Handlers {

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

        fileReader.clearBuffer();
        if (!fileReader.openReader(fileName)) {return false;}
        if (!fileReader.read()) {return false;}
        if (!fileReader.closeReader()) {return false;}
        if (lineNum == 0 && fileReader.getBuffer().size() > 0) {
            // удалим последнюю строку
            fileReader.getBuffer().remove(fileReader.getBuffer().size()-1);
        } else if (lineNum > fileReader.getBuffer().size()) {
            System.out.println("В файле нет строки с номером " + lineNum + ", всего строк: " + fileReader.getBuffer().size());
            fileReader.clearBuffer();
            return true;
        } else {
            fileReader.getBuffer().remove(lineNum-1);
        }
        if (!fileReader.openWriter(fileName)) {return false;}
        if (!fileReader.write()) {return false;}
        if (!fileReader.closeWriter()) {return false;}
        return true;
    }

}
