package testcases.C002E_DNSLOOKUP__CWE247;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

public class C002E_DNSLOOKUP__simple_01 {

    public void bad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        boolean trusted = false;
        String ip = req.getRemoteAddr();
        InetAddress addr = InetAddress.getByName(ip);

        // 도메인은 공격자에 의해 실행되는 서버의 DNS가 변경될 수 있으므로 안전하지 않다.
        /* FLAW : CWE-247 */
        if (addr.getCanonicalHostName().endsWith("trustme.com")) {
            trusted = true;
        }
    }

    public void good(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        boolean trusted = false;
        String ip = req.getRemoteAddr();
        if (ip == null || "".equals(ip)) return;

        // 이용하려는 실제 서버의 IP 주소를 사용하여 DNS변조에 방어한다.
        String trustedAddr = "127.0.0.1";
        if (ip.equals(trustedAddr)) {
            trusted = true;
        }
    }

}
