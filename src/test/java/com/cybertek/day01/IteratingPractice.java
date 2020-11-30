package com.cybertek.day01;

import org.junit.Test;

import java.sql.*;

public class IteratingPractice {


    // TASK 1 :
    //  CREATE A NEW CLASS , ADD CONNECTION , STATEMENT , RESULTSET
    // AND TRY TO PRINT OUT EACH AND EVERYTHING UNDER COUNTRIES TABLE

    @Test
    public void iteratingResultSet_test() throws SQLException {

        //DriverManager is used to get the connection using info below
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        //creating connection between java and database
        Connection connection = DriverManager.getConnection(connectionURL, username, password);

        //creating statement to write query
        Statement statement = connection.createStatement();

        //storing result of query
       // ResultSet result = statement.executeQuery("select * from countries where region_id = 1");
        ResultSet result = statement.executeQuery("select * from countries");



        while (result.next()){//as long as result has rows to iterate


            //getString(column name)  to get data at those columns
            System.out.println(result.getString("country_id")+" "
                    +result.getString("country_name")+" "
                    +result.getString("region_id"));
        }


        System.out.println("The end");


        result.close();
        statement.close();
        connection.close();


    }
}
