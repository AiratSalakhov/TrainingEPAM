package main.java.Salakhov.Lesson;

import java.io.*;
import java.util.LinkedList;

public class FileReader {

    private BufferedReader reader;
    private BufferedWriter writer;
    private LinkedList<String> buffer = new LinkedList<>();

    public boolean openReader(String fileName) {
        try {
            reader = new BufferedReader(new java.io.FileReader(fileName)) ;
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла для чтения!");
            System.out.println(e.getMessage());
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean openReaderSilent(String fileName) {
        try {
            reader = new BufferedReader(new java.io.FileReader(fileName)) ;
        } catch (IOException e) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            return false;
        }
        return true;
    }

    public boolean openWriter(String fileName) {
        try {
            writer = new BufferedWriter(new java.io.FileWriter(fileName)) ;
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла для записи!");
            System.out.println(e.getMessage());
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла после чтения!");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean closeWriter() {
        buffer.clear();
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла после записи!");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean read() {
        String line;
        buffer.clear();
        try {
            while ((line = reader.readLine()) != null) {
                buffer.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла!");
            System.out.println(e.getMessage());
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    public boolean write() {
        try {
            for (String line : buffer) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи файла!");
            System.out.println(e.getMessage());
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            return false;
        }
        buffer.clear();
        return true;
    }

    public LinkedList<String> getBuffer() {
        return buffer;
    }

    public void clearBuffer() {buffer.clear();}
}
