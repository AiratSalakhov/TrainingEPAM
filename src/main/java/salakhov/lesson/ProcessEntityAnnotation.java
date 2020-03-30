package salakhov.lesson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessEntityAnnotation {
    private static final int DEFAULT_AGE = 36;
    private static final String DEFAULT_NAME = "Default Name";
    private Human humanInstance;

    public boolean checkIfPresent(Class clazz) throws NoValueAnnotationException, IllegalStateException {
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        boolean hasValueAnnotation = false;
        if (clazz.isAnnotationPresent(Entity.class)) {
            System.out.println("entity annotation present for " + clazz);
            for (Method method : methods) {
                if (method.isAnnotationPresent(Value.class)) {
                    hasValueAnnotation = true;
                    break;
                }
            }
            if (!hasValueAnnotation) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Value.class)) {
                        hasValueAnnotation = true;
                        break;
                    }
                }
            }
            if (!hasValueAnnotation) {
                throw new NoValueAnnotationException("It's ok, but no Value annotation for " + clazz);
            }
            return true;
        }
        System.out.println("no entity annotation for " + clazz);
        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                hasValueAnnotation = true;
                break;
            }
        }
        if (!hasValueAnnotation) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    hasValueAnnotation = true;
                    break;
                }
            }
        }
        if (hasValueAnnotation) {
            throw new IllegalStateException("It's ok, but declared Value annotation for " + clazz);
        }
        return false;
    }

    public void searchEntityAnnotatedClasses() {
        Class[] clazz = Object.class.getDeclaredClasses();
        for (Object classObject : clazz) {
            System.out.println(classObject);
        }
    }

    public List<Human> generateHumans(String path) {
        String name = "";
        int age=0;
        List<Human> humanList = new ArrayList<>();
        Class<Human> human = Human.class;
        if (!path.trim().isEmpty()) {
            try {
                Scanner scanner = new Scanner(new FileReader(path));
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] params = line.split("=");
                    if (params.length != 2 || params[1].trim().isEmpty()) {
                        continue;
                    }
                    if (params[0].equalsIgnoreCase("name")) {
                        name = params[1].trim();
                    } else if (params[0].equalsIgnoreCase("age")) {
                        age = Integer.parseInt(params[1]);
                    } else {
                        continue;
                    }
                    if (!name.isEmpty() && age != 0) {
                        try {
                            Human humanInstance = human.newInstance();
                            humanInstance.setAge(age);
                            humanInstance.setName(name);
                            humanList.add(humanInstance);
                        } catch (InstantiationException | IllegalAccessException e) {
                            System.out.println(e);
                        }
                        name = "";
                        age = 0;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        return humanList;
    }

    public void setHumanValues(Human human) {
        if (human.getClass().isAnnotationPresent(Entity.class)) {
            Field[] fields = human.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    System.out.println("annotation value present for field " + field.getName());
                    Value annotation = field.getAnnotation(Value.class);
                    String annotationName = annotation.name();
                    int annotationAge = annotation.age();
                    String annotationPath = annotation.path();
                    if (!annotationPath.trim().isEmpty()) {
                        try {
                            Scanner scanner = new Scanner(new FileReader(annotationPath));
                            while (scanner.hasNext()) {
                                String line = scanner.nextLine();
                                String[] params = line.split("=");
                                if (params.length != 2 || params[1].trim().isEmpty()) {
                                    continue;
                                }
                                if (params[0].equalsIgnoreCase("name")) {
                                    annotationName = params[1].trim();
                                } else if (params[0].equalsIgnoreCase("age")) {
                                    annotationAge = Integer.parseInt(params[1]);
                                } else {
                                    continue;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println(e);
                        }
                    }
                    if (field.getName().equalsIgnoreCase("name")) {
                        try {
                            field.setAccessible(true);
                            if (annotationName.isEmpty()) {
                                field.set(human, DEFAULT_NAME);
                            } else {
                                field.set(human, annotationName);
                            }
                        } catch (IllegalAccessException e) {
                            System.out.println("setting field name - " + e);
                        }
                    } else if (field.getName().equalsIgnoreCase("age")) {
                        try {
                            field.setAccessible(true);
                            if (annotationAge != 0) {
                                field.set(human, annotationAge);
                            } else {
                                field.set(human, DEFAULT_AGE);
                            }
                        } catch (IllegalAccessException e) {
                            System.out.println("setting field age - " + e);
                        }
                    }
                }
            }
        }
    }
}