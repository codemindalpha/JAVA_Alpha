/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__URLConnection_setHeaderServlet_01.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-01.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: URLConnection Read data from a web server with URLConnection
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 01 Baseline
*
* */

package testcases.C000B_HTTPSPLIT__CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CWE113_HTTP_Response_Splitting_OpenEG2 extends AbstractTestCaseServletReturnString
{


	@RequestMapping(value="/test/csrf_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuffer buffer=new StringBuffer();
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String pinNo=request.getParameter("pinNo");
	    /* FLAW  */
		buffer.append("<p>사용자ID: "+userId+"</p>");
		buffer.append("<p>이메일: "+userName+"</p>");
		buffer.append("<p>PIN NO: "+pinNo+"</p>");

        return buffer.toString();
	}



	@Override
	public String good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return null;
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

