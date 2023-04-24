package testcases.C001F_EXCEAUTH__CWE307;/*
Filename : CWE307_Improper_Restriction_Of_Excessive_Authentication_Attempts__Servlet_Captcha_02_bad.java
*/

import testcasesupport.IO;
import testcasesupport.RUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;

public class C001F_CWE307_Improper_Restriction_Of_Excessive_Authentication_Attempts__Servlet_Captcha_02 {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final int FAIL = -1;
    private static final int MAX_ATTEMPTS = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) {
        String username;
        String password;
        Socket socket = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;
        String data = null;
        int OK = FAIL;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);

            // 로그인 정보를 잘못 입력하였을 경우 다시 입력을 시도하는데 있어 제한이 없다.
            // 공격자는 여러가지 비밀번호로 인증 재시도하여 정확한 비밀번호를 알아낼 수 있다.
            /* FLAW : CWE-307 */
            while (OK == FAIL) {
                // 사용자 이름과 패스워드를 입력받음
                username = request.getParameter("uesrname");
                password = request.getParameter("pw");

                OK = RUtil.verifyUser(username, password);
            }

            readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            data = readerBuffered.readLine();

            response.getWriter().println(data);

        } catch (UnknownHostException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (readerBuffered != null) readerBuffered.close();
                if (readerInputStream != null) readerInputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                IO.logger.log(Level.WARNING, "Error with stream reading", e);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) {
        String username;
        String password;
        Socket socket = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;
        String data = null;
        int OK = FAIL;
        int count = 0;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);

            // 로그인 정보를 잘못 입력하였을 경우 다시 입력을 시도하는데 있어 제한이 없다.
            // 공격자는 여러가지 비밀번호로 인증 재시도하여 정확한 비밀번호를 알아낼 수 있다.
            while (OK == FAIL  && (count < MAX_ATTEMPTS)) {
                // 사용자 이름과 패스워드를 입력받음
                username = request.getParameter("uesrname");
                password = request.getParameter("pw");

                OK = RUtil.verifyUser(username, password);
                count ++;
            }

            readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            data = readerBuffered.readLine();

            response.getWriter().println(data);

        } catch (UnknownHostException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (readerBuffered != null) readerBuffered.close();
                if (readerInputStream != null) readerInputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                IO.logger.log(Level.WARNING, "Error with stream reading", e);
            }
        }
    }
}