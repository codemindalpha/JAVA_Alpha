/*
Filename : KRD_301_TOC_TOU__basic_thread_0101_good.java
CWEID    : CWE367_TOC_TOU
sinkname : thread
sinkline : 39,69
makedate : 2012 08 20
license  : Copyright KISA.
*/

package testcases.C0020_TOCTOU__CWE367;

import java.io.*;

// from K
public class C0020_TOCTOU__basic_thread_0101_good
{
    public void good()
    {
		FileAccessThread2 fileAccessThread = new FileAccessThread2();
		FileDeleteThread2 fileDeleteThread = new FileDeleteThread2();
		fileAccessThread.start();
		fileDeleteThread.start();
    }
}

class FileAccessThread2 extends Thread
{
    /* FIX */
    public synchronized void run()
	{
    	BufferedReader br = null;
	    try
		{
		    File f = new File("c:\\toctou.txt");
			if(f.exists())
			{
			    br = new BufferedReader(new FileReader(f));
			}
		}catch(IOException e)
		{
		    System.err.println("IOException occured");
		}
	    finally
	    {
	    	try
	    	{
	    		if(br != null)
	    		{
	    			br.close();
	    		}
	    	}catch(IOException e)
	    	{
	    		System.err.println("Error closing BufferedReader");
	    	}
	    }
	}
}

class FileDeleteThread2 extends Thread
{
    /* FIX */
    public synchronized void run()
	{
	    File f = new File("c:\\toctou.txt");
		if(f.exists())
		{
		    f.delete();
		}
	}
}