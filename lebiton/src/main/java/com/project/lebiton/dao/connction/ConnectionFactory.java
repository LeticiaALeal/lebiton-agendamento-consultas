package com.project.lebiton.dao.connction;

import java.sql.*;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/lebiDb";
    private static final String USER = "root";
//    private static final String PASSWORD = "abc123.A";
    private static final String PASSWORD = "Leh2019";
//      private static final String PASSWORD = "1234";
  
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(final Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void closeConnection(final Connection connection, final PreparedStatement statement) throws SQLException {
        closeConnection(connection);

        if (statement != null) {
            statement.close();
        }
    }

    public static void closeConnection(final Connection connection, final PreparedStatement statement, final ResultSet resultSet) throws SQLException {
        closeConnection(connection, statement);

        if (resultSet != null) {
            resultSet.close();
        }
    }

}
