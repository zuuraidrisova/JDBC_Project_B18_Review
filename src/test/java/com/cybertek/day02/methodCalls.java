package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

public class methodCalls {

    @Test
    public void methodCall_test(){

        DB_Utility.createConnection();

        ResultSet result = DB_Utility.runQuery("select * from jobs");

        DB_Utility.displayAllData();

        System.out.println("-------------------------------------------------------------");

        int columnCount = DB_Utility.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        System.out.println("-------------------------------------------------------------");

        int rowcount = DB_Utility.getRowCount();
        System.out.println("rowcount = " + rowcount);

        System.out.println("-------------------------------------------------------------");

        List<String> row3DataAsList =  DB_Utility.getRowDataAsList(3);
        System.out.println("row 3 Data As List = " + row3DataAsList);

        System.out.println("-------------------------------------------------------------");

        List<String> column2DataAsList = DB_Utility.getColumnDataAsList(2);
        System.out.println("second column Data As List = " + column2DataAsList);

        System.out.println("-------------------------------------------------------------");

        List<String> columnNameDataAsList = DB_Utility.getColumnDataAsList("job_title");
        System.out.println("job title column Data As List = " + columnNameDataAsList);

        System.out.println("-------------------------------------------------------------");

        String dataAtRow3Column2 = DB_Utility.getColumnDataAtRow(3,2);
        System.out.println("data At Row 3 Column 2 = " + dataAtRow3Column2);

        System.out.println("-------------------------------------------------------------");

        String dataAtRow3ColumnName = DB_Utility.getColumnDataAtRow(3,"job_title");
        System.out.println("data At Row 3 Column job_title = " + dataAtRow3ColumnName);

        System.out.println("-------------------------------------------------------------");

        System.out.println("3rd Row Data As Map= " + DB_Utility.getRowDataAsMap(3));

        DB_Utility.destroy();
    }
}
