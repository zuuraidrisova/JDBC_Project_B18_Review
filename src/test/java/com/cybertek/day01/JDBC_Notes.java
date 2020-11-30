package com.cybertek.day01;

public class JDBC_Notes {
    /*

What is JDBC ?

Java Database Connectivity
	--  Comes with JDK , used to work with relational database system
	--  Its under java.sql package   import java.sql.* ;
	--  It define set of Interfaces and classes to work with any RDBMS

 Java App  ------ JDBC ------- Actual Database you are connecting to

 JDBC does not provide actual implementation.
 Each RDBMS(database vendor like Oracle, MySQL, Postgre) will provide
 actual implementation known as driver

 so oracle has oracle driver ( the one we are using thin driver)
 and mysql has connectorJ driver  and others has their own driver

 We used the maven dependency to import this driver

        <dependency>
            <groupId>com.oracle.ojdbc</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>19.3.0.0</version>
        </dependency>


  JDBC has DriverManager class to manage connection according to
  		the url | connection string  and username /password
  		to get connection object

  	url | connection string  (YOU WILL BE GIVEN THIS INFORMATION)
  	"jdbc:oracle:thin:@IP_ADDRESS:1521:XE";
  	jdbc  --  connection using java
  	oracle -- database vendor , RDBMS
  	thin  --- one type oracle driver
  	IP_ADDRESS  -- HOSTNAME or IP
  	1521  ---- port used for oracle database
  	XE   ----- sid name  unique identifier for your oracle db

 3 important Interfaces in JDBC

 	Connection
 		according to the connection string | url and username password provided
 		DriverManager will look for suitable driver of that RDBMS
 		and return the Connection object
 		Connection conn = DriverManager.getConnection(url,username,password)

 	Statement
 		we can use the Connection object created above to create Statement object
 		2 ways of creating statement object

 		// this will be used to create forward only ResultSet
 		// and we can not move up and down freely using this
 		Statement stmt = conn.createStatement();

 		// this will be used to created ResultSet that can move freely(backward, forward)
 		Statement stmt
 		= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );


 	ResultSet
 		This is used to store the result of the query run
 		and it has a lot of built in method to work with its data

		ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");

		Connection , Statement , ResultSet are all resources need to be closed after usage
		call the close method to close at the end of the code
		conn.close();
		stmnt.close();
		rs.close() ;

		the resultset contains all the data returned from the query
		it also has a cursor that point to the row you are currently at

		the cursor by default at before-first location ,  right before first row

		in order to get to the next row  --- next() method is used so we can read first row data

		rs.next()

	getting the data using the column names or column index
			getString(ColumName)
			getString(Index)
			getInt(ColumName)
			getInt(Index)
			getDouble(ColumName)
			getDouble(Index)
			getDate(ColumName)
			getDate(Index)

		rs.getString(1) --->> return the first column cell value at this row
		rs.getString("column name") --->> return the cell value at this column at this row

	----- other methods available under ResultSet class

		rs.next()       // move to next
		rs.previous()   // move to previous
		rs.first() 		// move to first row
		rs.last()		// move to last row
		rs.beforeFirst()  // back to before first row location
		rs.afterLast() 	  // move to the after last row location
		rs.absolute(rowNum)   // move specific row you specified


	---- Iterating over a ResultSet object to see all rows data

	// make sure we are at before first location
	  while( rs.next() == true ){

	  		print( rs.getString( "some column name here") ) ;

	  }

	  // make sure you are at after last location
	  while (rs.previous() == true ){

	  		print( rs.getString( "some column name here") ) ;

	  }

	  ---- we can get meta data out of our resultset object to
	  	get the information like Column names , data type , column count

	  // ResultSetMetadata is data about the ResultSet like column count , column name
        and many more info about the ResultSet itself

        // getMetaData method will return ResultSetMetaData object
        ResultSetMetaData rsmd = rs.getMetaData();

        // counting how many columns we have in our ResultSet object
        int columnCount =  rsmd.getColumnCount() ;

        System.out.println("columnCount = " + columnCount);

        // find out column name according to the index
        String secondColumnName = rsmd.getColumnName(2);
        System.out.println("secondColumnName = " + secondColumnName);

     */

}
