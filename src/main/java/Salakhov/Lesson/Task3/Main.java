package Salakhov.Lesson.Task3;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class testClass = customClassLoader.loadClass("TestClass");
        Object testClassInstance = testClass.newInstance();
        System.out.println(testClassInstance);
    }
}
