package testcases.C004B_SYSTEMEXIT;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class C004B_CWE382_Use_of_System_exit__bad_01 {
	private static Logger logger = Logger.getLogger(C004B_CWE382_Use_of_System_exit__bad_01.class);
	/*
	by juho
	## Included in the doPost() method defined below is a call to System.exit() in the event of a specific exception.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int action;
    //FLAW:
		System.exit(1);
	}

}
