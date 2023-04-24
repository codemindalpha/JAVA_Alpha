package testcases.C0025_USENULL__CWE476_NULL_Pointer_Dereference.s01;

import testcasesupport.*;

public class C0025_CWE476_NULL_Pointer_Dereference__GetProperty_String_01 extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String cmd = null;
		if(System.getProperty("cmd") != null)
			cmd = System.getProperty("cmd");
		// FLAW: cmd가 null인지 체크하지 않아 널 포인터 예외가 발생할 수 있다. 
		cmd = cmd.trim();
		System.out.println(cmd);
    }

    public void good() throws Throwable
    {
        String cmd = System.getProperty("cmd");
		// FIX: cmd가 null인지 체크해야 한다.
		if (cmd !=null) {
		  cmd = cmd.trim();
		  System.out.println(cmd);
		} else {
		  System.out.println("Null Command");
		}
    }

}

