package salakhov.lesson;

import org.slf4j.LoggerFactory;

public class Main {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ProcessEntityAnnotation processEntityAnnotation = new ProcessEntityAnnotation();
        try {
            processEntityAnnotation.checkIfPresent(Human.class);
        } catch (NoValueAnnotationException | IllegalStateException e) {
            log.info(e.getMessage());
        }
        try {
            processEntityAnnotation.checkIfPresent(Animal.class);
        } catch (NoValueAnnotationException | IllegalStateException e) {
            log.info(e.getMessage());
        }
        Human human = new Human();
        processEntityAnnotation.setHumanValues(human);
        log.info(human.toString());
        log.info(processEntityAnnotation.generateHumans("src/main/resources/multiAnnotation.txt").toString());
        processEntityAnnotation.searchEntityAnnotatedClasses();
    }
}
