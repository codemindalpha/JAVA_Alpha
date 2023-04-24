/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE315_Plaintext_Storage_in_Cookie__Servlet_01.java
Label Definition File: CWE315_Plaintext_Storage_in_Cookie__Servlet.label.xml
Template File: sources-sinks-01.tmpl.java
*/
/*
* @description
* CWE: 315 Storing plaintext data in a cookie
* BadSource:  Set data to credentials (without hashing or encryption)
* GoodSource: Set data to a hash of credentials
* Sinks:
*    GoodSink: Hash data before storing in cookie
*    BadSink : Store data directly in cookie
* Flow Variant: 01 Baseline
*
* */

package testcases.C0014_STORECLEAR__CWE312;

import testcasesupport.*;

import javax.naming.InitialContext;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class C0014_STORECLEAR__Servlet_01 extends AbstractTestCaseServlet
{
	private static final String CONNECT_STRING = "jdbc:ocl:orcl";
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	String sql = " insert into customer(id, pwd, name, ssn, zipcode, addr)"
    	+ " values (?, ?, ?, ?, ?, ?)";
    	
    	InitialContext ctx = new InitialContext();
    	DataSource datasource = (DataSource) ctx.lookup(CONNECT_STRING);
    	Connection conn = datasource.getConnection();
    	
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	stmt.setString(1, id);
    	stmt.setString(2, pwd);
    	/* FLAW: CWE-312 */
    	stmt.executeUpdate();

    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
    	Byte salt = 0x01; 
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	digest.reset();
    	digest.update(salt);
    	byte[] pwdDigested = digest.digest(pwd.getBytes());
    	
    	String sql = " insert into customer(id, pwd, name, ssn, zipcode, addr)"
    	+ " values (?, ?, ?, ?, ?, ?)";
    	
    	InitialContext ctx = new InitialContext();
    	DataSource datasource = (DataSource) ctx.lookup(CONNECT_STRING);
    	Connection conn = datasource.getConnection();
    	
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	stmt.setString(1, id);
    	stmt.setString(2, pwdDigested.toString());
    	
    	stmt.executeUpdate();
    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}

