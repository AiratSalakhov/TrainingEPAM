package salakhov.lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbProcessor {
    private static final String DB_PASSWORD = "SET_YOUR_PASSWORD_HERE!!!!!";
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                "postgres", DB_PASSWORD);

    }

    public static void insert(String newTitle) throws SQLException {
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
    }

    public static boolean isProdIdExists(int productId) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE prod_id = ?;");
        preparedStatement.setInt(1, productId);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public static void update(String newTitle, int productId) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE products SET title = ?" +
                " WHERE prod_id = ?;");
        preparedStatement.setString(1, newTitle);
        preparedStatement.setInt(2, productId);
        preparedStatement.executeUpdate();
    }

    public static List<String> search(String searchString) throws SQLException {
        List<String> stringList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM products " +
                searchString + ";");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String string = "";
            for (int i = 1; i < 8; i++) {
                string = string.concat(resultSet.getString(i).concat(" | "));
            }
            stringList.add(string);
        }
        return stringList;
    }
    public static void delete(int productId) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM products WHERE prod_id = ?;");
        preparedStatement.setInt(1, productId);
        preparedStatement.executeUpdate();
    }
    public static void disconnect() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
