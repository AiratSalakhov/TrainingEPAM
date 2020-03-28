package salakhov.lesson;

public class Main {
    public static void main(String[] args) throws NoValueAnnotationException, IllegalStateException {
        CheckEntityAnnotation checkEntityAnnotation = new CheckEntityAnnotation();
        try {
            checkEntityAnnotation.check(Human.class);
        } catch (NoValueAnnotationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        try {
            checkEntityAnnotation.check(Animal.class);
        } catch (NoValueAnnotationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
