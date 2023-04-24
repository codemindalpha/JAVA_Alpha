package testcases.C0012_WRONGPERM__CWE732;

/*
Filename : CWE732_Incorrect_Permission_Assignment_for_Critical_Resource__basic_File_01_bad.java
*/



import testcasesupport.*;

import java.io.*;

public class C0012_CWE732_Incorrect_Permission_Assignment_for_Critical_Resource__basic_File_02 extends AbstractTestCase2
{

 

    public void bad() throws Throwable
    {
    	File file = new File("/home/setup/system.ini");
    	/* FLAW : CWE-732 */
		file.setExecutable(true, false);
		file.setReadable(true, false);
		file.setWritable(true, false);
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



	@Override
	public void good() throws Throwable {
		File file = new File("/home/setup/system.ini");
		file.setExecutable(false);
		file.setReadable(true);
		file.setWritable(false);
	}
}

