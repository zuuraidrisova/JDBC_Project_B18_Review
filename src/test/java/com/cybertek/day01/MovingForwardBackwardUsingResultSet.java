package com.cybertek.day01;

import org.junit.Test;
import java.sql.*;

public class MovingForwardBackwardUsingResultSet {

    @Test
    public void movingFreelyWithCursor_test() throws SQLException {

        //DriverManager is used to get the connection using info below
        String connectionURL = "jdbc:oracle:thin:@35.153.51.63:1521:xe";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionURL, username, password);

        // if we create the Statement in this way , this will generate a forward only resultset
        // meaning we can only move forward with next() and can not move backward with previous()
        //Statement statement = conn.createStatement();

        // ResultSet.TYPE_SCROLL_INSENSITIVE will make the resultset created from this statement
        // be able to move forward and backward freely,
        // ResultSet.CONCUR_READ_ONLY  will make resultset readonly and that's what we need
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY );

        ResultSet result = statement.executeQuery("select * from countries");

        result.next(); // we were at column name row, so we moved to next row
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));

        result.next(); // we were at first row, so we moved to second row
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));


        result.previous(); // we were at second row, so we moved to first row back
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));

        //  moving to the last row directly
        result.last();
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));

        // this will move the cursor directly to the row we specify
        result.absolute(6);
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));


        // this will move the cursor to the first row location
        result.first();
        System.out.println(result.getString("country_id")+" "+result.getString("country_name"));

        // how to move to before first row location
        result.beforeFirst();
        // how to move to after last row location
        result.afterLast();

        result.close();
        statement.close();
        connection.close();

    }
}
