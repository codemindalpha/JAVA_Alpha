package testcases.C0024_NOCHKERR__CWE574;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class C0024_NOCHKERR__simple_01 {

    public void bad() {
        BufferedReader reader = null;
        try {
            URL url = new URL("http://openeg.co.kr/");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
            Date date = format.parse(line);
        /* FLAW : CWE-754 */
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.err.println("IOException : " + ex.toString());
                }
            }
        }
    }

    public void good() {
        BufferedReader reader = null;
        try {
            URL url = new URL("http://openeg.co.kr/");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
            Date date = format.parse(line);
        /* FIX */
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("ParseException : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.err.println("IOException : " + ex.toString());
                }
            }
        }
    }

}
