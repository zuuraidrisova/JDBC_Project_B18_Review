package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

public class DB_Utility_Practice4 {

    @Test
    public void db_utility_test(){

        DB_Utility.createConnection();

        ResultSet result = DB_Utility.runQuery("select * from regions");

        List<String> listOfDataAtRow4 = DB_Utility.getRowDataAsList(4);

        System.out.println("listOfDataAtRow4 = " + listOfDataAtRow4);

        System.out.println("-----------------------------------------------------------------");

        List<String> listOfDataAtColumn1 = DB_Utility.getColumnDataAsList(2);

        System.out.println("listOfDataAtColumn1 = " + listOfDataAtColumn1);

        System.out.println("-----------------------------------------------------------------");

        List<String> listOfDataAtColumnRegionName = DB_Utility.getColumnDataAsList("region_name");

        System.out.println("listOfDataAtColumnName = " + listOfDataAtColumnRegionName);

        DB_Utility.destroy();


    }
}
