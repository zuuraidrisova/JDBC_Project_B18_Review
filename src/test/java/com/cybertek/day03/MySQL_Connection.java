package com.cybertek.day03;

import com.cybertek.DB_Utilities.DB_Utility;
import org.junit.Test;

public class MySQL_Connection {

    @Test
    public void mySQL_database_test(){

        // added mysql pom dependency for mysql driver
        // we provided connection string , username and password
        // and used the utility to make connection

        String connectionStr = "jdbc:mysql://18.233.97.71:3306/library1";
        String username = "library1_client" ;
        String password = "WVF4NdGXCKHeE6VQ" ;

        DB_Utility.createConnection(connectionStr,username, password);

        int rowCount =  DB_Utility.getRowCount();

        System.out.println("rowCount = " + rowCount);

        DB_Utility.destroy();



    }
}
