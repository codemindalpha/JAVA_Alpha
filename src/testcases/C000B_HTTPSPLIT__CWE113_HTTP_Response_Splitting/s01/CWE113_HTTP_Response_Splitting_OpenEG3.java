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

public class CWE113_HTTP_Response_Splitting_OpenEG3 extends AbstractTestCaseServletReturnString
{


	@RequestMapping(value="/test/http_split_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		System.out.println("수신된 데이터: "+data);
		/* FLAW */
		response.setHeader("openeg", data);
//		Cookie cookie=new Cookie("data",data);
//		response.addCookie(cookie);
		buffer.append("HTTP 응답 분할 테스트: Cookie 값 확인");
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

