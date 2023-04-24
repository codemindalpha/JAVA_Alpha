package testcases.C0026_RESLEAK__CWE404_Improper_Resource_Shutdown.s01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CWE404__Improper_Resource_Shutdown__PrinterWriter_03 {
	public void badClose(File file, String enc) {
        PrintWriter out = null;
        try {

            out = new PrintWriter(
            	new OutputStreamWriter(
                new FileOutputStream(file), enc));
            out.append('c');
            /* FLAW : CWE-404 */
            if (out != null) {
                out.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	public void bad(File file, String enc) {
        badClose(file, enc);
	}
	
	public void goodClose(File file, String enc) {
        PrintWriter out = null;
        try {
            // Flaw:
            out = new PrintWriter(
            	new OutputStreamWriter(
                new FileOutputStream(file), enc));
            out.append('c');
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
	public void good(File file, String enc) {
        goodClose(file, enc);
	}
}
