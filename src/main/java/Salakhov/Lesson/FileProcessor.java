package Salakhov.Lesson;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileProcessor {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileProcessor.class.getName());
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private LinkedList<String> linkedList = new LinkedList<>();

    public boolean openReader(String fileName) {
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(fileName));
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла для чтения!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                    log.info(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean openReaderSilent(String fileName) {
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(fileName));
        } catch (IOException e) {
            log.info(e.getMessage());
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    log.info(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean openWriter(String fileName) {
        try {
            bufferedWriter = new BufferedWriter(new java.io.FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла для записи!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                    log.info(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean closeReader() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла после чтения!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean closeWriter() {
        linkedList.clear();
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла после записи!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean read() {
        String line;
        linkedList.clear();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                linkedList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                    log.info(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean write() {
        try {
            for (String line : linkedList) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи файла!");
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                    log.info(e1.getMessage());
                }
            }
            return false;
        }
        linkedList.clear();
        return true;
    }

    public LinkedList<String> getLinkedList() {
        return linkedList;
    }

    public void clearBuffer() {
        linkedList.clear();
    }
}
