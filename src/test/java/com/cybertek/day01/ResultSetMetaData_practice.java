package com.cybertek.day01;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMetaData_practice {

    @Test
    public void resultSetMetadata_test() throws SQLException {

        //ResultSetMetaData ==> is getting more information about the resultSet,
        //like column name, column count,data types and many more

        //DriverManager is used to get the connection using info below
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionURL, username, password);


        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet result = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = result.getMetaData();

        //getting column count using getColumnCount() coming from metadata class
        System.out.println("Column Count = " + metaData.getColumnCount());

        //getting column names using getColumnName() coming from metadata class
        System.out.println("Second Column Name = " + metaData.getColumnName(2));

        System.out.println("----------------------------------------------------");

        //how to list all the column name from the ResultSet
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++ ){

            String columnName = metaData.getColumnName(i);

            System.out.println("Number "+i+" ColumnName = " + columnName);
        }

        System.out.println("----------------------------------------------------");

        //storing column names into list
        List<String> listOfColumnNames = new ArrayList<>();

        for (int i = 1; i <= columnCount; i++){

            listOfColumnNames.add(metaData.getColumnName(i));

        }

        System.out.println("listOfColumnNames = " + listOfColumnNames);

        result.close();
        statement.close();
        connection.close();


    }
}
