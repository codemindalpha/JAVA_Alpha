package testcases.C0003_XSS__CWE80.s01;

import java.io.IOException;

import javax.servlet.http.*;

public class CWE80_XSS__Safe_01 {
	public static void v(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String p = request.getParameter("n");
		// good
		Integer i = Integer.parseInt(p);
		response.getWriter().println("<br>bad(): data = " + i);
	}
}