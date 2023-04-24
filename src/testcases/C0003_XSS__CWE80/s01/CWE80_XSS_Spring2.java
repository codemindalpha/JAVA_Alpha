package testcases.C0003_XSS__CWE80.s01;

import java.util.HashMap;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testcasesupport.AbstractTestCaseServletReturnString;

public class CWE80_XSS_Spring2 extends AbstractTestCaseServletReturnString{

	
	@RequestMapping(value="/test/xss_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	    HashMap<String,String> map = new HashMap<String,String>();
        map.put("1", "<script>alert('xss');</script>");
        map.put("2", "&lt;script&gt;alert('xss');&lt;/script&gt;");
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		if ("1".equals(data)) {
			/* FLAW */
			buffer.append(map.get("1"));
		}else if ("2".equals(data)) {
			/* FLAW */
			buffer.append(map.get("2"));
		} else {
			buffer.append("잘못된 요청입니다.");
		}
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