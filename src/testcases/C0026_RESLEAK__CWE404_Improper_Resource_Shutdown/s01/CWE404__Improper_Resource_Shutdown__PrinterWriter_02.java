package testcases.C0026_RESLEAK__CWE404_Improper_Resource_Shutdown.s01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CWE404__Improper_Resource_Shutdown__PrinterWriter_02 {
	// eclipse jdt can detect
	public void badNoClose(File file, String enc) {
		try {
			// Flaw:
			PrintWriter out = new PrintWriter(
					new OutputStreamWriter(
							new FileOutputStream(file), enc));
			out.append('c');
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void bad(File file, String enc) {
		badNoClose(file, enc);
	}
}
