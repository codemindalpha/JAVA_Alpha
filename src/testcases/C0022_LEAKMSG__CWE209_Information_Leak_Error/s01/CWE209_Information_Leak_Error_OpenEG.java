/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE209_Information_Leak_Error__printStackTrace_01.java
Label Definition File: CWE209_Information_Leak_Error.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 209 Information exposure through error message
* Sinks: printStackTrace
*    GoodSink: Print generic error message to console
*    BadSink : Print stack trace to console
* Flow Variant: 01 Baseline
*
* */

package testcases.C0022_LEAKMSG__CWE209_Information_Leak_Error.s01;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testcasesupport.*;

public class CWE209_Information_Leak_Error_OpenEG extends AbstractTestCaseServletReturnString
{
   

	@RequestMapping(value="/test/exception_test.do")
	@ResponseBody
	@Override
	public String bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		String type=request.getParameter("type");
		System.out.println("*****: "+data+"-----"+type);
		switch( Integer.parseInt(type)) {
		// 에러메시지를 통한 정보노출
		case 0:		
			int i=100/getInt(data);
			/* FLAW */
			buffer.append("처리결과: "+i);
			break;
	    // 에러처리부재
		case 1:
			  String level="S";
			  try {
			    if ( data.equals("admin")) {
			         level="S";
			    } else {
			         level="G";
			    }
			  }catch(Exception e){}

			  if ( level.equals("S"))  {
			      buffer.append("관리자 권한으로 작업을 수행합니다"); 
			  } else {
			      buffer.append("일반사용자 권한으로 작업을 수행합니다"); 
			  }
			  break;
        //부적절한 예외처리
		case 2:			
			char[] cbuf = new char[256];
		    FileReader fr;	
			FileWriter fw;
			try {
				fr = new FileReader(data);
				fw = new FileWriter(data+"_new");
				fw.write(new Date().getTime()+":  "+fr.read(cbuf));
			    fr.close();
			    fw.close();
			} catch(Exception e){
				buffer.append("요청하신 작업을 처리할 수 없습니다.");
			}
			break;
		default:
			buffer.append("요청오류");
			break;
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

