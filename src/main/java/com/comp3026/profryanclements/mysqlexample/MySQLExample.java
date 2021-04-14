package com.comp3026.profryanclements.mysqlexample;

import java.sql.*;

public class MySQLExample {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://YourDockerHostIPHere/mywebapp?"
                            + "user=mywebapp&password=thisismypasS$1");

            // Delete all rows from the mywebapp.users table to reset it back to zero
            statement = connect.createStatement();
            statement.executeUpdate( "delete from mywebapp.users");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from mywebapp.users");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  mywebapp.users values (default, ?, ?)");
            // "username, password, from mywebapp.users");
            // Parameters start with 1
            preparedStatement.setString(1, "testuser");
            preparedStatement.setString(2, "password");
            preparedStatement.executeUpdate();

            preparedStatement = connect
                    .prepareStatement("SELECT * from mywebapp.users");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
