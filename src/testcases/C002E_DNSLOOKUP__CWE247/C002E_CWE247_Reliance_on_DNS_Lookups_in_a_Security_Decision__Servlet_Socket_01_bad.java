package testcases.C002E_DNSLOOKUP__CWE247;/*
Filename : CWE247_Reliance_on_DNS_Lookups_in_a_Security_Decision__Servlet_Socket_01_bad.java
*/



import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class C002E_CWE247_Reliance_on_DNS_Lookups_in_a_Security_Decision__Servlet_Socket_01_bad
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;


        data = "adtest.com";



        Logger log_bad = Logger.getLogger("local-logger");
        InetAddress giriAddress = InetAddress.getByName(data);
        data =  giriAddress.getHostAddress();
        Socket sock = null;
        BufferedReader buffread = null;
        InputStreamReader instrread = null;
        try {
            /* Read data using an outbound tcp connection */
            /* FLAW */
            sock = new Socket(data, 9000);

            /* read input from socket */
            instrread = new InputStreamReader(sock.getInputStream());
            buffread = new BufferedReader(instrread);

            data = buffread.readLine();
        }
        catch( IOException ioe )
        {
            log_bad.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( buffread != null )
                {
                    buffread.close();
                }
            }
            catch( IOException ioe )
            {
                log_bad.warning("Error closing buffread");
            }
            finally {
                try {
                    if( instrread != null )
                    {
                        instrread.close();
                    }
                }
                catch( IOException ioe )
                {
                    log_bad.warning("Error closing instrread");
                }
            }

            /* clean up socket objects */
            try {
                if( sock != null )
                {
                    sock.close();
                }
            }
            catch( IOException e )
            {
                log_bad.warning("Error closing sock");
            }
        }


    }
}

