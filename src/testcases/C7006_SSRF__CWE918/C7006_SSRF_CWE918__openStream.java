package testcases.C7006_SSRF__CWE918;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

public class C7006_SSRF_CWE918__openStream extends HttpServlet {
    protected void bad(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        String url = req.getParameter("url");

        InputStream inputStream = null;
        OutputStream outputStream = null;
        // 사용자에게 url을 입력받음
        URL u = new URL(url);
        res.setHeader("content-disposition","attachment;fileName=''");

        int length;
        byte[] bytes = new byte[1024];
        // 입력받은 URL을 stream으로 생성
        /* FLAW: CWE-918 */
        inputStream = u.openStream();
        outputStream = res.getOutputStream();
        while ((length = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0 ,length);
        }
    }

    // key, value 형식으로 URL의 리스트를 작성.
    private Map<String, URL> urlMap;

    protected void good(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        URL u = urlMap.get(req.getParameter("url"));
        res.setHeader("content-disposition","attachment;fileName=''");

        int length;
        byte[] bytes = new byte[1024];
        // 입력받은 URL을 stream으로 생성
        inputStream = u.openStream();
        outputStream = res.getOutputStream();
        while ((length = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0 ,length);
        }
    }
}
