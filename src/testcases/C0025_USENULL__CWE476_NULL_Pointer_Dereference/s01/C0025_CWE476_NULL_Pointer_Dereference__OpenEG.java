package testcases.C0025_USENULL__CWE476_NULL_Pointer_Dereference.s01;

import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testcasesupport.*;


public class C0025_CWE476_NULL_Pointer_Dereference__OpenEG extends AbstractTestCaseServletReturnString
{
	
	@RequestMapping(value="/test/null_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		   StringBuffer buffer=new StringBuffer();			
	        String data=request.getParameter("data");
	        String userid=request.getParameter("userid");
	        try {
		        FileReader fr=new FileReader("c:/SecureCoding/eclipse/eclipse.ini");	
		        /* FLAW  */
		        if( userid.equals("admin")) {
		        	switch(getInt(data)){
		        	case 0: buffer.append("백업 작업을 수행합니다."); break;
		        	case 1: buffer.append("복구 작업을 수행합니다."); break;
		        	case 2: buffer.append("실행 작업을 수행합니다."); break;
		        	default: buffer.append("선택된 작업이 없습니다.");
		        	}
		        } else {
		        	buffer.append("작업권한이 없습니다.");
		        }
	        }catch(Exception e) {
	        	buffer.append("파일 초기화 작업을 수행합니다."); 
	        }
		 
       return buffer.toString();	
	}

	@Override
	public String good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static  int getInt(String data){
		int i=-1;
		try {
		    i= Integer.parseInt(data);
		}catch(NumberFormatException e){
			return i;
		}
		return i;
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

