package main.java.Salakhov.Lesson.Handlers;

import main.java.Salakhov.Lesson.FileReader;

public class HandlerAdd implements main.java.Salakhov.Lesson.Handlers.Handlers {

    FileReader fileReader = new FileReader();

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

        stringToAdd = stringToAdd.substring(1, stringToAdd.length()-1);

        if (!fileReader.openReader(fileName)) {return false;}
        if (!fileReader.read()) {return false;}
        if (!fileReader.closeReader()) {return false;}
        if (lineNum == 0 || (lineNum - fileReader.getBuffer().size()) == 1) {
            // добавим строку в конец
            fileReader.getBuffer().add(stringToAdd);
        } else if (lineNum > fileReader.getBuffer().size()) {
            int emptyLinesToAdd = lineNum - fileReader.getBuffer().size();
            for (int i=1; i < emptyLinesToAdd; i++) {
                fileReader.getBuffer().add("");
            }
            fileReader.getBuffer().add(stringToAdd);
        } else {
            fileReader.getBuffer().add(lineNum-1, stringToAdd);
        }
        if (!fileReader.openWriter(fileName)) {return false;}
        if (!fileReader.write()) {return false;}
        if (!fileReader.closeWriter()) {return false;}
        return true;
    }
}
