package com.cybertek.day01;

import java.sql.*;

public class DB_Connection {

    public static void main(String[] args) throws SQLException {

        //Connection --> Statement --> ResultSet

        //DriverManager is used to get the connection
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        //creating connection object using DRiverManager's static method getConnection()
        //getConnection() accepts 3 args for connecting, which are url, username, password
        Connection connection = DriverManager.getConnection(connectionURL, username, password);

        //creating a statement using connection we established
        Statement statement = connection.createStatement();

        //store the result coming from query
        ResultSet resultSet = statement.executeQuery("select * from regions");

        //resultset comes with cursor to navigate between rows
        resultSet.next();

        System.out.println("first column value using index: --> "+ resultSet.getString(1));
        System.out.println("first column value using column name: --> "+ resultSet.getString("REGION_ID"));

        System.out.println("first column value using index: --> "+ resultSet.getString(2));
        System.out.println("first column value using column name: --> "+ resultSet.getString("REGION_NAME"));

        // try to move to next row and get second row data as a task
        resultSet.next();

        System.out.println("second column value using index: --> "+ resultSet.getString(1));
        System.out.println("second column value using column name: --> "+ resultSet.getString("REGION_ID"));

        System.out.println("second column value using index: --> "+ resultSet.getString(2));
        System.out.println("second column value using column name: --> "+ resultSet.getString("REGION_NAME"));



        System.out.println("the end");


        //close all resources

        resultSet.close();
        statement.close();
        connection.close();

    }
}
