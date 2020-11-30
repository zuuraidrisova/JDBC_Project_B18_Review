package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Utility_Practice2 {

    @Test
    public void db_utility_test() throws SQLException {

        DB_Utility.createConnection();//connects to database using our utility method

        //run the query and store it to resultSet using our utility method
        ResultSet result = DB_Utility.runQuery("select * from jobs");

        //to learn column count using our utility method
        int columnCount = DB_Utility.getColumnCount();

        while (result.next()) {//looping thru the rows

            for (int i = 1; i <= columnCount; i++) {//looping thru the columns

                System.out.print(result.getString(i) + " ");
            }

            System.out.println();//to break the line after each row since we used only print
        }

        System.out.println("-----------------------------------------------------------------");

        DB_Utility.displayAllData();

        DB_Utility.destroy();

    }

}
