/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE539_Information_Exposure_Through_Persistent_Cookie__Servlet_01.java
Label Definition File: CWE539_Information_Exposure_Through_Persistent_Cookie__Servlet.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 539 Information Exposure Through Persistent Cookie
* Sinks:
*    GoodSink: Do not use a persistent cookie
*    BadSink : Use a persistent cookie
* Flow Variant: 01 Baseline
*
* */

package testcases.C001B_PERSCOOKIE__CWE539_Information_Exposure_Through_Persistent_Cookie.s01;

import testcasesupport.AbstractTestCaseServlet;
import testcasesupport.IO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;

public class CWE539_Information_Exposure_Through_Persistent_Cookie__simple_1 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String username = request.getParameter("username");
        char[] password = request.getParameter("password").toCharArray();
        boolean rememberMe = Boolean.valueOf(request.getParameter("rememberme"));
        if(rememberMe) {
            Cookie loginCookie = new Cookie("rememberme", "YES");

            /* FLAW: CWE539, Make the cookie persistent, by setting the expiration to 1 years */
            loginCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(loginCookie);
        } else{
            IO.logger.log(Level.INFO, "No cookie");
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        good1(request, response);
    }

    private void good1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String username = request.getParameter("username");
        char[] password = request.getParameter("password").toCharArray();
        boolean rememberMe = Boolean.valueOf(request.getParameter("rememberme"));

        if(rememberMe) {
            Cookie loginCookie = new Cookie("rememberme", "YES");
            // FIX
            loginCookie.setMaxAge(60*60*24);
            response.addCookie(loginCookie);
        } else{
            IO.logger.log(Level.INFO, "No cookie");
        }
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

