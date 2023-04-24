/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE497_Exposure_of_System_Data__Servlet_12.java
Label Definition File: CWE497_Exposure_of_System_Data__Servlet.label.xml
Template File: point-flaw-12.tmpl.java
*/
/*
* @description
* CWE: 497 Exposure of System Data to an Unauthorized Control Sphere
* Sinks:
*    GoodSink: no exposing
*    BadSink : expose the path variable to the user
* Flow Variant: 12 Control flow: if(IO.static_returns_t_or_f())
*
* */

package testcases.C002B_SYSTEMLEAK__CWE497_Exposure_of_System_Data.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;

public class CWE497_Exposure_of_System_Data__Servlet_12 extends AbstractTestCaseServlet
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if (IO.staticReturnsTrueOrFalse())
        {
            /* FLAW: system information exposed */
            response.getWriter().println("Not in path: " + System.getenv("PATH"));
        }
        else {

            /* FIX: error message is general */
            response.getWriter().println("Not in path");

        }
    }

    /* good1() changes the "if" so that both branches use the GoodSink */
    private void good1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        if(IO.staticReturnsTrueOrFalse())
        {
            /* FIX: error message is general */
            response.getWriter().println("Not in path");
        }
        else {

            /* FIX: error message is general */
            response.getWriter().println("Not in path");

        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        good1(request, response);
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
