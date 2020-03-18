package Salakhov.Lesson.Task3;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    //public CustomClassLoader(ClassLoader parentClassLoader) {super(parentClassLoader);}
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
        byte b[] = fetchClassFromFS("C:/Java/myClasses/" + className + ".class");
        return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));

        long length = new File(path).length();
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Ошибка чтения файла "+path);
        }
        is.close();
        return bytes;
    }
}
