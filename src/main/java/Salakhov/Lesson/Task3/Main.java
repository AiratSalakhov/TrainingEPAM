package Salakhov.Lesson.Task3;

public class Main {
       /*
       Task 3
       Написать свой загрузчик класса, который будет подгружать классы из директории вне вашего проекта.
       - Создайте директорию myClasses вне вашего проекта
               - В этой директории создайте класс TestClass, где переопределите метод toString, чтобы он выводил какой-то текст.
               - Реализуйте загрузчик класса CustomClassLoader, который будет подгружать классы из директории myClasses.
       При написании загрузчика нужно ориентироватся на последний слайд в презентации.
       - Проверить, что ваш загрузчик классов работает корректно можно следующим способом:

       CustomClassLoader classLoader = new CustomClassLoader();
       Class clazz = classLoader.loadClass("TestClass");
       Object obj = c.newInstance();
       System.out.println(obj);

       - Текст, который выведет метод println должен совпадать с текстом из переопределенного метода toString из класса TestClass
        */
       public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
              CustomClassLoader classLoader = new CustomClassLoader();
              Class clazz = classLoader.loadClass("TestClass");
              Object obj = clazz.newInstance();
              System.out.println(obj);
       }

}
