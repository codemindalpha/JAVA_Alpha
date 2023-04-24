package testcases.C0026_RESLEAK__CWE404_Improper_Resource_Shutdown.s01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CWE404__Improper_Resource_Shutdown__PrinterWriter_01 {
	// eclipse jdt cannot detect
	public void badAssign(File file, String enc) throws IOException {
		PrintWriter out = null;
		try {
			try {
                // Flaw:
				out = new PrintWriter(
						new OutputStreamWriter(
								new FileOutputStream(file), enc));
			} catch (UnsupportedEncodingException ue) {
				out = new PrintWriter(new FileWriter(file));
			}
			out.append('c');
		} catch (IOException e) {
		} finally {
			if (out != null) {
				
			}
		}
	}
	public void bad(File file, String enc) throws IOException {
		badAssign(file, enc);
	}
	
	public void goodAssign(File file, String enc) throws IOException {
		PrintWriter out = null;
		try {
			try {
				out = new PrintWriter(
						new OutputStreamWriter(
								new FileOutputStream(file), enc));
			} catch (UnsupportedEncodingException ue) {
				out = new PrintWriter(new FileWriter(file));
			}
			out.append('c');
		} catch (IOException e) {
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	public void good(File file, String enc) throws IOException {
		goodAssign(file, enc);
	}
}
