package testcases.C000A_CSRF__CWE352;/*
Filename : CWE352_Cross_Site_Request_Forgery__getCookiesServlet_write_01_bad.java
*/



import javax.servlet.http.*;

import java.util.logging.Logger;

public class C000A_CWE352_Cross_Site_Request_Forgery__getCookiesServlet_write_01_bad
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        data = ""; /* initialize data in case there are no cookies */

        /* Read data from cookies */
        {
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null)
            {
                
                data = cookieSources[0].getValue();
            }
        }
		
        /* FLAW: untrusted input with CSRF prevention mechanism */
        response.getWriter().write(data);

    }


}

