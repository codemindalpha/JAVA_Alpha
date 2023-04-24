package testcases.C006E_DMANAG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class C006E_CWE245_Direct_Management_of_Connections__bad_01 {
	String DB_DATASRC_REF = "s";
	/*

	by juho 17. 12. 11

	## In the following example, the class DatabaseConnection opens and manages a connection to a database for a J2EE application. The method openDatabaseConnection opens a connection to the database using a DriverManager to create the Connection object conn to the database specified in the string constant CONNECT_STRING.
	*/

	private static final String CONNECT_STRING = "jdbc:mysql://localhost:3306/mysqldb";
	private Connection conn = null;
	public C006E_CWE245_Direct_Management_of_Connections__bad_01() { int action; }

	public void bad() {
		try {
			//FLAW:
			conn = DriverManager.getConnection(CONNECT_STRING);
		} catch (SQLException ex) { int action; }
	}

	/*
	## The use of the DriverManager class to directly manage the connection to the database violates the J2EE restriction against the direct management of connections. The J2EE application should use the web application container's resource management facilities to obtain a connection to the database as shown in the following example.
	*/


	public void good() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource datasource = (DataSource) ctx.lookup(DB_DATASRC_REF);
			conn = datasource.getConnection();
		} catch (NamingException ex) { int action; }
		catch (SQLException ex) { int action; }
	}

	// Member functions for retrieving database connection and accessing database
	// …


	// public class DatabaseConnection {
  //
	// }

}
