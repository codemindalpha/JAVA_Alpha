/*
Filename : KRD_301_TOC_TOU__basic_canRead_0101_good.java
CWEID    : CWE367_TOC_TOU
sinkname : canRead
sinkline : 40,
makedate : 2012 08 20
license  : Copyright KISA.
*/

package testcases.C0020_TOCTOU__CWE367;

import testcasesupport.*;

import java.io.*;

// from K
public class C0020_TOCTOU__basic_canRead_0101_good
{


    public void good() throws Throwable
    {
        good1();
    }

    private void good1() throws Throwable
    {

        java.util.logging.Logger log_gsnk = java.util.logging.Logger.getLogger("local-logger");

        File f = new File("c:\\toctou.txt"); /* may need to be adjusted depending on script */

        BufferedReader bufread2 = null;
        InputStreamReader inread2 = null;
        FileInputStream finstr2 = null;

        String line = "";
        try
        {
            /* FIX: Remove delay */
            finstr2 = new FileInputStream("c:\\toctou.txt");
            inread2 = new InputStreamReader(finstr2);
            bufread2 = new BufferedReader(inread2);

            while((line = bufread2.readLine()) != null)
            {
                IO.writeLine(line);
            }
        }
        catch(IOException e)
        {
            log_gsnk.warning("Error reading from console");
        }
        finally
        {
            try
            {
                if( bufread2 != null )
                {
                    bufread2.close();
                }
            }
            catch( IOException e )
            {
                log_gsnk.warning("Error closing bufread2");
            }
            finally
            {
                try
                {
                    if( inread2 != null )
                    {
                        inread2.close();
                    }
                }
                catch( IOException e )
                {
                    log_gsnk.warning("Error closing inread2");
                }
                finally
                {
                    try
                    {
                        if( finstr2 != null )
                        {
                            finstr2.close();
                        }
                    }
                    catch( IOException e )
                    {
                        log_gsnk.warning("Error closing finstr2");
                    }
                }
            }
        }

    }
}

