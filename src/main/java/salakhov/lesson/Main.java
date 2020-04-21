package salakhov.lesson;


import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {
    private static ResultSet resultSet;
    private static Statement statement;
    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException {
        //Class.forName("org.postgresql.Driver");
        try {

            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres",
                    "postgres", "GiveMe1000backs");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from products;");
            while (resultSet.next()) {
                log.info(resultSet.getString(3));
            }
        } catch (SQLException e) {
            log.info("SQL Exception {}", e.getMessage());
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.info("SQL Exception {}", e.getMessage());
            }
        }

    }
}
