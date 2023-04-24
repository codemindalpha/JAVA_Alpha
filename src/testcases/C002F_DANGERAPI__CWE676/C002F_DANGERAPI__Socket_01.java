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

public class C002F_DANGERAPI__Socket_01 {

    public class Bad extends javax.servlet.http.HttpServlet {
        private Socket socket;

        protected void bad(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException {
            try {
                // 프레임워크의 메소드 호출 대신 소켓을 직접사용하고 있어 프레임워크에서 제공하는 보안기능을 제공 받지 못해 안전하지 않다
                /* FLAW : CWE-676 */
                socket = new Socket("kisa.or.kr", 8080);
            } catch (UnknownHostException e) {
                IO.logger.log(Level.WARNING, e.toString());
            } catch (IOException e) {
                IO.logger.log(Level.WARNING, e.toString());
            }
        }
    }

    public class Good extends javax.servlet.http.HttpServlet {

        protected void good(HttpServletRequest request,
                             HttpServletResponse response) {
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
        }
    }

}
