package Bean;

import ex01.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginBean {
//    public boolean login(String username, String password) {
//        return username.equalsIgnoreCase("ty") && password.equalsIgnoreCase("123");
//    }

    boolean isValid = false;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean login(String username, String password) {
        try {
            connection = ConnectDatabase.getConnection();
            preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            isValid = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }
}