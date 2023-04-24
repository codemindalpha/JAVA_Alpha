package testcases.C0003_XSS__CWE80.s01;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testcasesupport.AbstractTestCaseServletReturnString;

public class CWE80_XSS_Spring1 extends AbstractTestCaseServletReturnString{

	
	@RequestMapping(value="/test/xss_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		/* FLAW  */
		buffer.append(data);
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