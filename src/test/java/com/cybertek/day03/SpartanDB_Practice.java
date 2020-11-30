package com.cybertek.day03;

import static com.cybertek.DB_Utilities.DB_Utility.*;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class SpartanDB_Practice {

    @Test
    public void spartan_test(){

        DB_Utility.createConnection("test");

        //Run query "SELECT * FROM EMPLOYEES"
        ResultSet result = DB_Utility.runQuery("select * from spartans");

        //    1. display all data in spartan table
       // DB_Utility.displayAllData();

        System.out.println("-------------------------------------------------------");

        //    2. Print column count
       int columnCount =  DB_Utility.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        System.out.println("-------------------------------------------------------");

        //    3. Print row count
        int rowCount = DB_Utility.getRowCount();
        System.out.println("rowCount = " + rowCount);

        System.out.println("-------------------------------------------------------");

        //    4. Print out 3rd row data as a list
        List<String>  row3Data = DB_Utility.getRowDataAsList(3);
        System.out.println("row3Data = " + row3Data);

        System.out.println("-------------------------------------------------------");

        //    5. Print out 2nd column data as a list
        List<String> column2Data = DB_Utility.getColumnDataAsList(2);
        System.out.println("column2Data = " + column2Data);

        System.out.println("-------------------------------------------------------");

        //    6. Print out Name column data as a list
        List<String> columnNameData = DB_Utility.getColumnDataAsList("Name");
        System.out.println("columnNameData = " + columnNameData);

        System.out.println("-------------------------------------------------------");

        //    7. Print out 4th row as a Map
        Map<String, String> row4Data = DB_Utility.getRowDataAsMap(4);
        System.out.println("row4Data = " + row4Data);

        System.out.println("-------------------------------------------------------");

        //    8. Print out the data at row 5, column 1
        String row5Column1Data = DB_Utility.getColumnDataAtRow(5,1);
        System.out.println("row5Column1Data = " + row5Column1Data);

        System.out.println("-------------------------------------------------------");

        //    9. Print out the data at row 53, phone column
        String row53ColumnPhoneData = DB_Utility.getColumnDataAtRow(53,"phone");
        System.out.println("row53ColumnPhoneData = " + row53ColumnPhoneData);

        System.out.println("-------------------------------------------------------");

        //    10. Print out all the data as List of Map
        List<Map<String , String>> allDataAsListOfMap = DB_Utility.getAllDataAsListOfMap();
        System.out.println("allDataAsListOfMap = " + allDataAsListOfMap);


        DB_Utility.destroy();
    }
}
