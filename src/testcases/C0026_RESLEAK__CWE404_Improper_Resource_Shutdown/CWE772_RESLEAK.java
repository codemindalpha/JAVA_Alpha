package C0026_RESLEAK__CWE404_Improper_Resource_Shutdown;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class CWE772_RESLEAK {

    private void bad1(String fName) throws IOException {
        BufferedReader fil = new BufferedReader(new FileReader(fName));
        String line;
        while ((line = fil.readLine()) != null) {
            System.out.println(line);
        }
    }

    private void bad2(String some_connection_string) {
        try {
            Connection con = DriverManager.getConnection(some_connection_string);
        }
        catch ( Exception e ) {
            System.out.println(e);
        }
    }

    private void good(String fName) throws IOException {
        BufferedReader fil = new BufferedReader(new FileReader(fName));
        String line;
        while ((line = fil.readLine()) != null) {
            System.out.println(line);
        }
        fil.close();
    }
}
