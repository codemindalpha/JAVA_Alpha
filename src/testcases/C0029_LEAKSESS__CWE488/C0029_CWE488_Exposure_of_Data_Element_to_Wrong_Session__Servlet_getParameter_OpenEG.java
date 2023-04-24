package testcases.C0029_LEAKSESS__CWE488;

/*
Filename : CWE488_Exposure_of_Data_Element_to_Wrong_Session__Servlet_getParameter_01_bad.java
*/



import testcasesupport.*;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class C0029_CWE488_Exposure_of_Data_Element_to_Wrong_Session__Servlet_getParameter_OpenEG extends AbstractTestCaseServletReturnString
{

	@RequestMapping(value="/test/session_data_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuffer buffer=new StringBuffer();	
		CustomerService cust=CustomerService.getInstance();	
		/* FLAW */
		Customer c=new Customer(request.getParameter("name"),
	                                                  request.getParameter("phone"));
		
		//session.setAttribute("customer", cust.getCustomer());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			buffer.append("요청 작업을 처리할 수 없습니다: 10001");
		}
		//Customer c=(Customer)session.getAttribute("customer");
		synchronized (this) {
			cust.setCustomer(c);
			buffer.append(cust.displayCustomer());
		}
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

