package com.cybertek.day02;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

import java.sql.ResultSet;

public class DB_Utility_Practice5 {

    @Test
    public void db_utility_test(){

        DB_Utility.createConnection();

        ResultSet result = DB_Utility.runQuery("select * from countries");

        System.out.println("3rd Row Data As Map= " + DB_Utility.getRowDataAsMap(3));

        DB_Utility.destroy();
    }
}
