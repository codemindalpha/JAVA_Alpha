package testcases.C7006_SSRF__CWE918;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class C7006_SSRF_CWE918__openConn {
    protected void bad(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //사용자 입력값으로 url을 받음.
        URL url = new URL(req.getParameter("url"));
        /* FLAW: CWE-918 */
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }

    // key, value 형식으로 URL의 리스트를 작성.
    private Map<String, URL> urlMap;

    protected void good(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 사용자에게 urlMap의 key를 입력받아 urlMap에서 URL 값을 참조.
        URL url = urlMap.get(req.getParameter("url"));
        // urlMap에서 참조한 값으로 Connection을 만들어 접속
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }
}
