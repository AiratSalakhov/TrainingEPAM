package salakhov.lesson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CheckEntityAnnotation {
    public boolean check(Class clazz) throws NoValueAnnotationException, IllegalStateException {
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
                    }
                }
            }
            if (!hasValueAnnotation) {
                throw new NoValueAnnotationException("It's ok, but no Value annotation for " + clazz);
            }
            return true;
        } else {
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
                    }
                }
            }
            if (hasValueAnnotation) {
                throw new IllegalStateException("It's ok, but declared Value annotation for " + clazz);
            }
            return false;
        }
    }
}
