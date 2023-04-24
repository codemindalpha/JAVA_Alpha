package testcases.C7006_SSRF__CWE918;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class C7006_SSRF_CWE918__openConnProp {
    Properties properties = null;

    protected void bad(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        URL url = new URL(properties.getProperty("connectUrl"));
        /* FLAW: CWE-918 */
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }

    protected void good(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String urlstr = properties.getProperty("connectUrl");

        if(!urlstr.startsWith("https://api.company.com/api/public")){
            throw new RuntimeException(); // InvalidUrlException();
        }

        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }
}
