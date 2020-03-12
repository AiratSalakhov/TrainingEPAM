package main.java.Salakhov.Lesson;

import main.java.Salakhov.Lesson.Handlers.HandlerAdd;
import main.java.Salakhov.Lesson.Handlers.HandlerDelete;
import main.java.Salakhov.Lesson.Handlers.HandlerPrint;

import java.util.HashMap;

public class Filter {

    HashMap<String, main.java.Salakhov.Lesson.Handlers.Handlers> hashMapHandlers = new HashMap<>();

    main.java.Salakhov.Lesson.Handlers.HandlerAdd handlerAdd = new HandlerAdd();
    main.java.Salakhov.Lesson.Handlers.HandlerDelete handlerDelete = new HandlerDelete();
    main.java.Salakhov.Lesson.Handlers.HandlerPrint handlerPrint = new HandlerPrint();

    public Filter() {
        hashMapHandlers.put("add", handlerAdd);
        hashMapHandlers.put("delete", handlerDelete);
        hashMapHandlers.put("print", handlerPrint);
    }

    public boolean parseCommand (String commandToDo, Integer lineNum, String fileName, String stringToAdd) {
        if (!hashMapHandlers.containsKey(commandToDo)) {
            System.out.println("Неизвестная команда " + commandToDo);
            return false;
        }
        if (lineNum < 0) {
            System.out.println("Номер строки должен быть положительным!");
            return false;
        }

        //System.out.println("Запуск обработчика " + commandToDo + "...");
        return hashMapHandlers.get(commandToDo).execute(lineNum, fileName, stringToAdd);
    }
}
