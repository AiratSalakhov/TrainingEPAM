package main.java.Salakhov.Lesson;

import Salakhov.Lesson.Handlers.HandlerAdd;
import Salakhov.Lesson.Handlers.HandlerDelete;
import Salakhov.Lesson.Handlers.HandlerPrint;

import java.util.HashMap;

public class Filter {

    HashMap<String, Object> hashMapHandlers = new HashMap<>();

    Salakhov.Lesson.Handlers.HandlerAdd handlerAdd = new HandlerAdd();
    Salakhov.Lesson.Handlers.HandlerDelete handlerDelete = new HandlerDelete();
    Salakhov.Lesson.Handlers.HandlerPrint handlerPrint = new HandlerPrint();

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
        System.out.println("Исполнение команды ...");

        return true;
    }
}
