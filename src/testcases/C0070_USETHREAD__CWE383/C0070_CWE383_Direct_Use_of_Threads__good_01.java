package testcases.C0070_USETHREAD;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
public class C0070_CWE383_Direct_Use_of_Threads__good_01 extends HttpServlet{
	/*
	 * #In the following example, a new Thread object is created and invoked directly from within the body of a doGet() method in a Java servlet.
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Perform servlet tasks.
		// Do not create thread
		int action;
	}
}
