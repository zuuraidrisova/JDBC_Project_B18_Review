package com.cybertek.day01;

import org.junit.Test;

import java.sql.*;

public class IteratingResultSet {


    @Test
    public void iteratingResultSet_test() throws SQLException {

        //Connection --> Statement --> ResultSet

        //DriverManager is used to get the connection
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        //create a connection between java project and database using url
        Connection connection = DriverManager.getConnection(connectionURL, username, password);

        //creating a statement to be able to query the database
        Statement statement = connection.createStatement();

        //writing actual query and storing it to resultSet
        ResultSet result = statement.executeQuery("select * from regions");

        //next() returns boolean, as long as it returns true, loop will run,
        // once next() is false, it will exit the loop
        //next() is used to move the cursor to the first row
        while (result.next()){//while we don not run out of rows , print the data

            //getString(column index)  to get data at those columns
            System.out.println(result.getString(1)+" " + result.getString(2));
        }


        System.out.println("The end");

        result.close();
        statement.close();
        connection.close();




    }
}
