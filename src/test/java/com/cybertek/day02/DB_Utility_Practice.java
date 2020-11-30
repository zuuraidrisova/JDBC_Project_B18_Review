package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DB_Utility_Practice {

    @Test
    public void DB_Utility_test() throws SQLException {

        //printing all data from jobs table
        DB_Utility.createConnection();

        ResultSet result = DB_Utility.runQuery("select * from jobs");

        //thru iterating, until it is false, print all data
        while(result.next()) {

            System.out.println(result.getString(1) + " " + result.getString(2));

        }

        System.out.println("---------------------------------------------------");

        int columnCount = DB_Utility.getColumnCount();

        System.out.println("columnCount = " + columnCount);

        System.out.println("---------------------------------------------------");

        // what if we want to print out everything in the resultSet
        // without knowing the column names
        //System.out.println(  rs.getString(1) ...2 .3.4.5..6.6.6.7.  );

        result.first();//we have to move the cursor to the first row so we
                        // can start printing everything from all over
        for(int i = 1; i <= columnCount; i++){

            System.out.print(result.getString(i)+" ");
        }

        System.out.println();

        System.out.println("---------------------------------------------------");


        DB_Utility.destroy();
    }



}
