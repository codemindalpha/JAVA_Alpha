package testcases.C000D_RELUNTRUST;

/*
Filename : CWE732_Incorrect_Permission_Assignment_for_Critical_Resource__basic_File_01_bad.java
*/



import testcasesupport.*;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class C000D_RELUNTRUST_OpenEG2 extends AbstractTestCaseServletReturnString
{

	@RequestMapping(value="/test/init_db.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 
		StringBuffer buffer=new StringBuffer();
		String id = request.getParameter("id");
		/* FLAW */
		if ( id.equals(request.getSession().getAttribute("userId")) && "admin".equals(id)) {
		     testcases.C000D_RELUNTRUST.DBinit util=new testcases.C000D_RELUNTRUST.DBinit();
		    // util.initMSSQLDB();
		     util.initMySQLDB();
		     buffer.append("DB 초기화 완료");
		} else {
			buffer.append("작업권한이 없습니다.");
		}
		request.getSession().invalidate();
		response.sendRedirect("/openeg/login.do");
        return buffer.toString();
	}

	@Override
	public String good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}


	/* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
	public static void main(String[] args) throws ClassNotFoundException,
	InstantiationException, IllegalAccessException
	{
		mainFromParent(args);
	}


}

