package com.cybertek.DB_Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    //we are making all fields static so we can access them in all other methods
    private static Connection connection;
    private  static Statement statement;
    private static ResultSet result;
    private static ResultSetMetaData metaData;



    /**
     * a static method to create connection between java application and database
     * using valid url, username and password
     */
    public static void createConnection(){

        //DriverManager is used to get the connection using info below
        String connectionURL = ConfigurationReader.getProperty("database.url");
        String username = ConfigurationReader.getProperty("database.username");
        String password = ConfigurationReader.getProperty("database.password");

        try {

            connection = DriverManager.getConnection(connectionURL, username, password);

        } catch (SQLException exception) {

            System.out.println("Connection has failed!");
            exception.printStackTrace();


        }

        //function call inside another to create a connection
       // createConnection(connectionURL, username, password);

    }



    /**
     * overloading createConnection() method to be able to pass parameters
     * configuration parameters as url, username, password
     */
    public static void createConnection(String env){

        String connectionStr = ConfigurationReader.getProperty(env+".database.url");
        String username = ConfigurationReader.getProperty(env+".database.username");
        String password = ConfigurationReader.getProperty(env+".database.password");

        createConnection(connectionStr, username, password);
        //just calling another method which creates a connection in order not to duplicate

        System.out.println("You are in "+env+" environment");
    }


    /**
     * overloading createConnection() method to be able to pass parameters
     * configuration parameters as url, username, password
     */
    public static void createConnection(String connectionUrl, String username, String password){

        try{

            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connection Successful");
        }catch (SQLException exception){

            System.out.println("Connection has failed");
            exception.printStackTrace();
        }


    }




    /**
     * a static method to get the ResultSet object with valid connection by executing query
     * query will be stored into ResultSet so we can manipulate the data in it later
     */

    public static ResultSet runQuery(String query){


        try {

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                             ResultSet.CONCUR_READ_ONLY);

            result = statement.executeQuery(query);

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return result;
    }



    /**
     * create a method to get column count of current result set as an int
     */
    public static int getColumnCount(){

        int columnCount = 0;

        try {

            metaData = result.getMetaData();
            columnCount = metaData.getColumnCount();

        } catch (SQLException exception) {

            System.out.println("Error while counting the columns");
            exception.printStackTrace();
        }

        return columnCount;
    }


    /**
     * create a static method to get row count as an int
     */
    public static int getRowCount(){

        int rowCount = 0;

        try {

             result.last();//we have to move the cursor to last row to get row count
             rowCount = result.getRow();

             //after getting row count we have to move to before first location in case
            result.beforeFirst();

        }catch (SQLException exception){

            System.out.println("Error while getting the row count");
            exception.printStackTrace();
        }

        return rowCount;
    }



    /**
     * create a void method to display all data  in a table we get running the query
     */
    public static void displayAllData(){

        //getting the column count using our utility method
        int columnCount = DB_Utility.getColumnCount();

        try {
            //now the cursor might be in any location
            //in order to iterate from the very first row, we have to move the cursor there
            result.beforeFirst();

            while(result.next()){//looping thru the rows

                for(int i = 1; i <= columnCount; i++){//looping thru the columns

                    System.out.print(result.getString(i)+" ");
                }

                System.out.println();//to break the line after print
            }

            //now the cursor is at after last location
      //move it back to before first location so we can have no issues calling other methods
            result.beforeFirst();//this is for next method that might need to be at before first location


        }catch (SQLException exception){

            System.out.println("Error while displaying all data");

            exception.printStackTrace();
        }


    }


    /**
     * create a method to get a single column cell data at certain row
     * ex: row 2 column 3 ---> the data
     * method should except 2 int parameters as row number and column index
     * * @param rowNumber ---> row number we want to get data from
     * * @param columnIndex --> column index we want to get the data from
     * and return data as string
     */
    public static String getColumnDataAtRow(int rowNumber, int columnIndex){

        String data = "";

        try {

            result.absolute(rowNumber);//to go to certain row we specified

            data = result.getString(columnIndex);

        }catch (SQLException exception){

            System.out.println("Error while getting data of column data at certain row");
            exception.printStackTrace();
        }

        return data;

    }



    /**
     * create an overloaded static method to get a single column cell data at certain row
     * ex: row 2 column 3 ---> the data
     * method should except 1 int parameter as row number
     * and  one String parameter as column name
     * * @param rowNumber ---> row number we want to get data from
     * * @param columnName --> column name we want to get the data from
     * and return data as string
     */
    public static String getColumnDataAtRow(int rowNumber, String columnName){

        String data = "";

        try {

            result.absolute(rowNumber);//to go to certain row we specified

            data = result.getString(columnName);

        }catch (SQLException exception){

            System.out.println("Error while getting data of column data at certain row");
            exception.printStackTrace();
        }

        return data;

    }


    /**
     * create a static method to get the entire row data as List<String>
     *  method will accept one int parameter as row number   we want to get the data from
     */
    public static List<String> getRowDataAsList(int rowNumber){

        List<String> listOfData = new ArrayList<>();

        try {

            result.absolute(rowNumber);//we have to be in this specified row to get data

            //iterate each column and add the value into list
            //we call our method getColumnCount() to specify the number of columns
            for (int i = 1; i <= getColumnCount(); i++){

                listOfData.add(result.getString(i));

            }

        }catch (SQLException exception){

            System.out.println("Error while getting row data and storing it into a list");
            exception.printStackTrace();
        }

        return listOfData;
    }



    /**
     * create a static method to get entire column data and store it as List<String>
     *  method should return List<String>
     *  accept one int parameter as column index
     */
    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataList = new ArrayList<>();

        try{

            result.beforeFirst();//moving the cursor to the before first location

            while(result.next()) {//until we ran out of rows please iterate and store data into list

               columnDataList.add( result.getString(columnIndex) );

            }
            result.beforeFirst();//moving the cursor to the before first location after we are done

        }catch (SQLException exception){

            System.out.println("Error while getting column data and storing it into list");
            exception.printStackTrace();
        }

        return columnDataList;
    }



    /**
     *  create an overloaded static method to get entire column data
     *  and store it as List<String>
     *  method should return List<String>
     *  accept one String parameter as column name
     */
    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataList = new ArrayList<>();

        try{

            result.beforeFirst();//moving the cursor to the before first location

            while(result.next()) {//until we ran out of rows please iterate and store data into list

                columnDataList.add( result.getString(columnName) );

            }
            result.beforeFirst();//moving the cursor to the before first location after we are done

        }catch (SQLException exception){

            System.out.println("Error while getting column data and storing it into list");
            exception.printStackTrace();
        }

        return columnDataList;
    }



    /**
     * create a static method to get certain row data as map and return Map<String, String>
     * method should accept one int parameter as row number
     * we want to store certain row data as a map
     * give me number 3 row ===> Map<String, String> ()
     */
    public static Map<String, String> getRowDataAsMap(int rowNumber){

        Map<String, String> dataAsMap = new LinkedHashMap<>();//LinkedHashMap keeps insertion order

        try{

            result.absolute(rowNumber);

            metaData = result.getMetaData();

            for (int columnNumber = 1; columnNumber <= getColumnCount(); columnNumber++){

                String columnName = metaData.getColumnName(columnNumber);
                String columnData =  result.getString(columnNumber);

                dataAsMap.put(columnName,columnData);

            }

        }catch (SQLException exception){

            System.out.println("Error while getting data at certain row and storing it into map");
            exception.printStackTrace();
        }

        return dataAsMap;
    }



    /**
     *
     * @return The entire resultset as List of Row Map
     *
     * so it's like : List = { map of 1st row}, { map of 2nd row}, {map of 3rd row}..
     */

    public static List<Map<String,String> > getAllDataAsListOfMap(){

        List<Map<String,String> > rowMapList = new ArrayList<>();

        for (int i = 1; i <= getRowCount(); i++) {

            rowMapList.add(   getRowDataAsMap(i)    ) ;
        }

        return rowMapList ;
    }




    /**
     * create a void method to close all open resources
     */
    public static void destroy(){

        try {

            if(result != null) {//if resultSet is not closed

                result.close();//then close it
            }

            if(statement != null){//if Statement is not closed

                statement.close();//then close it
            }

            if(connection != null) {//if Connection is not closed

                connection.close();//then close it
            }

        }catch (SQLException exception){

            System.out.println("ERROR WHILE CLOSING");
            exception.printStackTrace();
        }

    }


}
