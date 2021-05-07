package JDBC;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnectPostgre {

    Connection connection;
    Statement statement;

    PreparedStatement preparedStatement;
    CallableStatement callableStatement;

    ResultSet resultSet;
    ResultSetMetaData resultSetMetaData;

    @Test
    void simpleQuery() throws SQLException {
        //once database baglantisi yapilir. get connection 3 argument alir. url-user-password
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres", "Ankara06");
        //olusturulan connection uzerinden Statement
        statement = connection.createStatement();
        /*
        statement.execute();
        statement.executeQuery();
        statement.executeUpdate();
        */
        resultSet = statement.executeQuery("select city from customers;");

        List<String> myData = new ArrayList<>();

        while (resultSet.next())
            //myData.add(resultSet.getString("city"));
            myData.add(resultSet.getString(1));

        System.out.println("myData = " + myData);
    }
    @Test
    void update() throws SQLException {
        //once database baglantisi yapilir. get connection 3 argument alir. url-user-password
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres", "Ankara06");

        statement = connection.createStatement();
        /*
        statement.executeUpdate();
        */
        int row = statement.executeUpdate("update customers\n" +
                                            "set city = 'no data'\n" +
                                            "where city is null;");
        System.out.println(row + " rows updated");

    }





}
