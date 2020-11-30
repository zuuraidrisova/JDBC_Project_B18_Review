package com.cybertek.day03;

public class JDBC_Notes {
    /*

What is JDBC ?

Java Database Connectivity
	--  Comes with JDK , used to work with relational database system
	--  Its under java.sql package   import java.sql.* ;
	--  It define set of Interfaces and classes to work with any RDBMS

 Java App   ------ JDBC ------- Actual Database you are connecting to

 JDBC does not provide actual implementation.
 Each RDBMS(database vendor like Oracle, MySQL, Postgrass) will provide
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

  	url | connection string  (YOU WILL BE GIVE THIS INFORMATION)
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

 		// this will be used to created ResultSet that can move freely
 		Statement stmt
 		= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );


 	ResultSet
 		This is used to store the result of the query run
 		and it has a lot of built in method to work with its data

		ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");


		Connection , Statement , ResultSet are all resources need to be closed after usage
		call the close() method to close at the end of the code
		rs.close() ;
		stmnt.close();
		conn.close();


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

		rs.next()       // move to next row
		rs.previous()   // move to previous row
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
        // and many more info about the ResultSet itself

        // getMetaData method will return ResultSetMetaData object
        ResultSetMetaData rsmd = rs.getMetaData();

        // counting how many columns we have in our ResultSet object
        int columnCount =  rsmd.getColumnCount() ;
        System.out.println("columnCount = " + columnCount);

        // find out column name according to the index
        String secondColumnName = rsmd.getColumnName(2);
        System.out.println("secondColumnName = " + secondColumnName);


-----  eventually we will create a utility class with many reusable methods
		to hire all the complexity so we can directly say
		run this query and store the result to a data structure like list or map
		or Lits<Map<>>

---- few scenario you will use your database knowledge

	 * test case require the data added through the UI is persisted in the database
	 * the data dispayed on the UI side is actually coming from database
	 * get the latest up to date data to work with your existing scenario

	 	assuming you are working on amazon order details page
	 	you will need to have a valid order id (which can not be made up )
	 	to get those details

	 	Assuming the order info is stored in ORDER table
	 	write a query to grab valid order id with valid state ()
	 	then run your UI test with that valid data

	 	SELECT order_id from ORDER where status = 'SHIPPING' ;

	 	--- how do you handle dynamic data in UI
	 	-- for example the data work on expire once you run your test
	 	-- and next time you run the test it will fail because of data

	 	--  I will make a connection with the database using JDBC
	 	--  I query the database to get valid data ,
	 	-- and use that data to run the test


Utility class  :
		static fields|variables
			    private static Connection conn;
			    private static Statement stmnt;
			    private static ResultSet rs ;

		static methods :

		public static void createConnection()
			PENDING : public static void createConnection(url, user, password)

		public static ResultSet runQuery(String query)
		public static void destroy()
		public static int getRowCount()
		public static int getColumnCNT()

		public static List<String> getColumnDataAsList(int columnIndex)
		public static List<String> getColumnDataAsList(String columnName)

		public static List<String> getColumnDataAtRow (int rowNum , int columnIndex)
		public static List<String> getColumnDataAtRow (int rowNum , String columnName)

		public static Map<String,String> getRowMap( int rowNum )
		public static List<Map<String,String>> getAllDataAsListOfMap()

		add more..


Database connectionString , username password are
	information should be kept outside source code

	SQL --- Programming language to access , manipulate relational database

	MYSQL -- like Oracle Database , is a RDBMS --Relational Database Management System


	Oracle SQL Developer is the IDE by Oracle to easily work with oracle database

	each database vendors usually have their own IDE and yet support different database

	MYSQL database has its specific IDE called MYSQL Workbench

	Oracle SQL Developer also support connecting to other databases by adding their driver
	so if we connect to MYSQL database we provide mysql database driver so we can make connection

Download MYSQL driver

	    go to sql developer
		go to preference (mac user  COMMAND + comma(,) )
		go to preference (windows user , tools . preference )

		select database from right panel , expand it
		you will see Third party database driver
		Click on add entry -- find your driver file and click select
     */

}
