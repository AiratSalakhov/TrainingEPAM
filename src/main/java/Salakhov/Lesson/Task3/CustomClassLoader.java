package Salakhov.Lesson.Task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    private static Logger log = LoggerFactory.getLogger(CustomClassLoader.class.getName());

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte bytes[] = fetchClassFromFS("C:/Java/myClasses/" + className + ".class");
            log.info("File with class loaded!");
            return defineClass(className, bytes, 0, bytes.length);
        } catch (FileNotFoundException ex) {
            log.error("File with class not found! " + ex.getLocalizedMessage());
            return super.findClass(className);
        } catch (IOException ex) {
            log.error("IO error while loading class file! " + ex.getLocalizedMessage());
            return super.findClass(className);
        }
    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream inputStream = new FileInputStream(new File(path));
        long length = new File(path).length();
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length &&
                (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Error reading file " + path);
        }
        inputStream.close();
        return bytes;
    }
}
