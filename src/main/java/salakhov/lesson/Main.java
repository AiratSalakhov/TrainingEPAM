package salakhov.lesson;

public class Main {
    public static void main(String[] args) throws NoValueAnnotationException, IllegalStateException {
        ProcessEntityAnnotation processEntityAnnotation = new ProcessEntityAnnotation();
        try {
            processEntityAnnotation.checkIfPresent(Human.class);
        } catch (NoValueAnnotationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        try {
            processEntityAnnotation.checkIfPresent(Animal.class);
        } catch (NoValueAnnotationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        Human human = new Human();
        processEntityAnnotation.setHumanValues(human);
        System.out.println(human);

        System.out.println(processEntityAnnotation.generateHumans("src/main/resources/multiAnnotation.txt").toString());

        processEntityAnnotation.searchEntityAnnotatedClasses();
    }
}
