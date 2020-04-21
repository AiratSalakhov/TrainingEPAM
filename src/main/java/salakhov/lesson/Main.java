package salakhov.lesson;


import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Scanner;

@Slf4j
public class Main {
    private static final String DB_PASSWORD = "SET_YOUR_PASSWORD_HERE!!!!!";
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            String inputLine;
            Scanner scanner = new Scanner(System.in);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                    "postgres", DB_PASSWORD);
            forExit:
            while (true) {
                System.out.println("Таблица products (prod_id, category, title, actor, price, special, common_prod_id)");
                System.out.println("1 - добавить запись, <пробел> значение_title (остальное по умолчанию)");
                System.out.println("2 - изменить запись, <пробел> prod_id <пробел> новое_значение_title");
                System.out.println("3 - просмотреть запись, <пробел> искомая_строка в title");
                System.out.println("4 - удалить запись, <пробел> prod_id");
                System.out.println("5 - выход");
                inputLine = scanner.nextLine().trim();
                Scanner scanner1 = new Scanner(inputLine);
                if (!scanner1.hasNextInt()) {
                    continue;
                }
                int command = scanner1.nextInt();
                switch (command) {
                    case (1): {
                        if (!scanner1.hasNextLine()) {
                            System.out.println("Не указан title!");
                            break;
                        }
                        String newTitle = scanner1.nextLine().trim();
                        preparedStatement = connection.prepareStatement("INSERT INTO products (" +
                                "category, title, actor, price, special, common_prod_id) VALUES " +
                                "(?,?,?,?,?,?);");
                        preparedStatement.setInt(1, 10);
                        preparedStatement.setString(2, newTitle);
                        preparedStatement.setString(3, "NEW ACTOR");
                        preparedStatement.setFloat(4, 55.55f);
                        preparedStatement.setShort(5, (short) 1);
                        preparedStatement.setInt(6, 212);
                        preparedStatement.executeUpdate();
                        break;
                    }
                    case (2): {
                        if (!scanner1.hasNextInt()) {
                            System.out.println("Не указан prod_id!");
                            break;
                        }
                        int productId = scanner1.nextInt();
                        String newTitle = scanner1.nextLine().trim();
                        preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE prod_id = ?;");
                        preparedStatement.setInt(1, productId);
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                            System.out.println("Не найден prod_id!");
                            break;
                        }
                        preparedStatement = connection.prepareStatement("UPDATE products SET title = ?" +
                                " WHERE prod_id = ?;");
                        preparedStatement.setString(1, newTitle);
                        preparedStatement.setInt(2, productId);
                        preparedStatement.executeUpdate();
                        break;
                    }
                    case (3): {
                        String searchString = scanner1.hasNext() ? ("WHERE title LIKE '%" +
                                scanner1.nextLine().trim() + "%'") : "";
                        preparedStatement = connection.prepareStatement("SELECT * FROM products " +
                                searchString + ";");
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            for (int i = 1; i < 8; i++) {
                                System.out.print(resultSet.getString(i).concat(" | "));
                            }
                            System.out.println();
                        }
                        break;
                    }
                    case (4): {
                        if (!scanner1.hasNextInt()) {
                            System.out.println("Не указан prod_id!");
                            break;
                        }
                        int productId = scanner1.nextInt();
                        preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE prod_id = ?;");
                        preparedStatement.setInt(1, productId);
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                            System.out.println("Не найден prod_id!");
                            break;
                        }
                        preparedStatement = connection.prepareStatement("DELETE FROM products WHERE prod_id = ?;");
                        preparedStatement.setInt(1, productId);
                        preparedStatement.executeUpdate();
                        break;
                    }
                    case (5): {
                        break forExit;
                    }
                }
            }
        } catch (SQLException e) {
            log.info("SQL Exception {}", e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.info("SQL Exception {}", e.getMessage());
            }
        }
        log.info("Приложение завершено!");
    }
}
