package com.cybertek.day01;

import org.junit.Test;

import java.sql.*;

public class IteratingResultSetBackward {

    @Test
    public void iteratingResultSetBackward_test() throws SQLException {

        //DriverManager is used to get the connection
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        //create a connection between java project and database using url
        Connection connection = DriverManager.getConnection(connectionURL, username,password);

        //creating a statement to be able to query the database
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //writing actual query and storing it to resultSet
        ResultSet result = statement.executeQuery("select * from regions");

        //first move to after last location

        result.afterLast();//it will go to the row after last data

        while(result.previous()) {//this will go to the last row now
            //and iterate until it will reach till the beginning of table

            System.out.println(result.getString(1) + " " + result.getString(2));

        }

        System.out.println("---------------------------------------------");

        result.absolute(2);
        System.out.println(result.getString(1)+" "+result.getString(2));

        System.out.println("---------------------------------------------");

        result.first();
        System.out.println(result.getString(1)+" "+result.getString(2));

        System.out.println("---------------------------------------------");

        result.last();
        System.out.println(result.getString(1)+" "+result.getString(2));

         //how to know what row i am right now
        // there is no count method in ResultSet
        // so in order to get the row count of the resultset
        // just return the last row number by moving cursor to last row and call getRow method
        System.out.println("result.getRow() = " + result.getRow());

        result.close();
        statement.close();
        connection.close();


    }




}
