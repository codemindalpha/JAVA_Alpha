package testcases.C002F_DANGERAPI__CWE676;

import testcasesupport.IO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;

public class C002F_DANGERAPI__Exit_01 {

    void bad(HttpServletRequest request, HttpServletResponse response) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/DataServlet");
            // 보안기능을 제공하는 프레임워크의 메소드를 사용하여야한다.
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
        } catch (MalformedURLException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
        // J2EE 프로그램에서 System.exit()을 사용하여 서비스가 종료될 수 있다.
        /* FLAW : CWE-676 */
        System.exit(1);
    }

    void good(HttpServletRequest request, HttpServletResponse response) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/DataServlet");
            // 보안기능을 제공하는 프레임워크의 메소드를 사용하여야한다.
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
        } catch (MalformedURLException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
        // 서비스 종료를 막기 위해 J2EE에서는 System.exit()를 사용하지 않는다.
    }

}