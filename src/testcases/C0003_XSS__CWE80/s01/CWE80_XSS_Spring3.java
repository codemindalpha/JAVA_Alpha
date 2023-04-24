package testcases.C0003_XSS__CWE80.s01;

import java.io.IOException;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testcasesupport.AbstractTestCaseServlet;

public class CWE80_XSS_Spring3 extends AbstractTestCaseServlet{

	
	@RequestMapping(value="/test/xss_test_c.do")
	@ResponseBody
	@Override
	public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String data=request.getParameter("data");
        try {
        	   /* FLAW */
	         response.sendRedirect("../domxss.jsp?message="+data);
        } catch (IOException e) {
	
          System.out.println("ERROR");
        }
		
	}

	@Override
	public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
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