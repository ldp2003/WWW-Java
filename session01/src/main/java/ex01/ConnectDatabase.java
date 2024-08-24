package ex01;


import java.sql.Connection;

public class ConnectDatabase {
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sapassword";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Test";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }
}
