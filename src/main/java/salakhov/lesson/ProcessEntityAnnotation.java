package salakhov.lesson;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ProcessEntityAnnotation {
    private static final int DEFAULT_AGE = 36;
    private static final String DEFAULT_NAME = "Default Name";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());
    private static Collection<File> names = new ArrayList<>();

    public static void processFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry);
                continue;
            }
            names.add(entry);
        }
    }

    public void checkIfPresent(Class clazz) throws NoValueAnnotationException, IllegalStateException {
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        boolean hasValueAnnotation = false;
        if (clazz.isAnnotationPresent(Entity.class)) {
            log.info("entity annotation present for " + clazz);
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
            return;
        }
        log.info("no entity annotation for " + clazz);
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
    }

    public void searchEntityAnnotatedClasses() {
        String folderPath = ProcessEntityAnnotation.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        folderPath = folderPath.substring(1);
        processFilesFromFolder(new File(folderPath));

        for (File file : names) {
            String name = file.getPath();
            if (!name.endsWith(".class")) {
                continue;
            }
            name = name.substring(0, name.length() - ".class".length());
            name = name.replace("/", ".");
            name = name.replace("\\", ".");
            int classesPos = name.indexOf(".classes.");
            if (classesPos >= 0) {
                name = name.substring(classesPos + ".classes.".length());
            }
            try {
                if (Class.forName(name).isAnnotationPresent(Entity.class)) {
                    log.info("Annotation Entity present for class " + name);
                } else {
                    log.info("No annotation Entity present for class " + name);
                }
            } catch (ClassNotFoundException nfe) {
                System.out.println("class not found exception - " + nfe);
            }
        }
    }

    public List<Human> generateHumans(String path) {
        String name = "";
        int age = 0;
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
                        try {
                            age = Integer.parseInt(params[1]);
                        } catch (NumberFormatException e) {
                            log.info("Invalid age in file - " + e.getMessage());
                        }
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
                            log.info(e.getMessage());
                        }
                        name = "";
                        age = 0;
                    }
                }
            } catch (FileNotFoundException e) {
                log.info(e.getMessage());
            }
        }
        return humanList;
    }

    public void setHumanValues(Human human) {
        if (human.getClass().isAnnotationPresent(Entity.class)) {
            Field[] fields = human.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    log.info("annotation value present for field " + field.getName());
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
                                    try {
                                        annotationAge = Integer.parseInt(params[1]);
                                    } catch (NumberFormatException e) {
                                        log.info("Invalid age in file - " + e.getMessage());
                                    }
                                }
                            }
                        } catch (FileNotFoundException e) {
                            log.info(e.getMessage());
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
                            log.info("setting field name - " + e);
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
                            log.info("setting field age - " + e);
                        }
                    }
                }
            }
        }
    }
}