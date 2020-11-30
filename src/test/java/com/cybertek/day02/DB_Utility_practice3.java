package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

public class DB_Utility_practice3 {

    //printing all data in a table we get thru running the query
    @Test
    public void db_utility_test(){

        DB_Utility.createConnection();

        ResultSet result = DB_Utility.runQuery("select * from regions");

        // this method call is displaying the data of the resultset
        DB_Utility.displayAllData();

        System.out.println("-----------------------------------------------------------------");

        String dataAtRow3Column2 = DB_Utility.getColumnDataAtRow(3,2);

        System.out.println("data At Row 3 Column 2 = " + dataAtRow3Column2);

        System.out.println("-----------------------------------------------------------------");

        String dataAtRow3ColumnName = DB_Utility.getColumnDataAtRow(3,"region_name");

        System.out.println("data At Row 3 Column Name = " + dataAtRow3ColumnName);

        System.out.println("-----------------------------------------------------------------");

        int rowCount = DB_Utility.getRowCount();

        System.out.println("row Count = " + rowCount);

        System.out.println("-----------------------------------------------------------------");

        int columnCount = DB_Utility.getColumnCount();

        System.out.println("column Count = " + columnCount);

        DB_Utility.destroy();
    }


}
