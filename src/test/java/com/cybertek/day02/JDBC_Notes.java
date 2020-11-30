package com.cybertek.day02;

public class JDBC_Notes {
    /*

----- eventually we will create a utility class with many reusable methods
to hire all the complexity so we can directly say
run this query and store the result to a data structure like list or map
or Lits<Map<>>

---- few scenario you will use your database knowledge

* test case require the data added through the UI is persisted in the database
* the data displayed on the UI side is actually coming from database
* get the latest up to date data to work with your existing scenario

ex:
assuming you are working on amazon order details page
you will need to have a valid order id (which can not be made up )
to get those details

Assuming the order info is stored in ORDER table
write a query to grab valid order id with valid state ()
then run your UI test with that valid data

ex:
SELECT order_id from ORDER where status = 'SHIPPING' ;

--- how do you handle dynamic data in UI?
-- for example the data you work on, expires once you run your test
    and next time you run the test it will fail because of data

-- I will make a connection with the database using JDBC
-- I query the database to get valid data ,
-- and use that data to run the test

Utility class :

static fields|variables
private static Connection connection;
private static Statement statement;
private static ResultSet result;

static methods :

public static void createConnection()
public static ResultSet runQuery(String query)
public static void destroy()
public static int getColumnCNT()
public static int getRowCount()

public static List<String> getColumnDataAsList(int columnIndex)
public static List<String> getColumnDataAsList(String columnName)

public static String getColumnDataAtRow (int rowNum , int columnIndex)
public static String getColumnDataAtRow (int rowNum , String columnName)

public static Map<String,String> getRowMap( int rowNum )
public static List<Map<String,String>> getAllDataAsListOfMap()

     */
}
