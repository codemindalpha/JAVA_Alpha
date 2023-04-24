package testcases.C000B_HTTPSPLIT__CWE113_HTTP_Response_Splitting.s01;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C000B_HTTPSPLIT__simple_01 {

    public void bad(HttpServletRequest request, HttpServletResponse response){
        String lastLogin = request.getParameter("last_login");
        if (lastLogin == null || "".equals(lastLogin)) {
            return;
        }
        // 쿠키는 Set-Cookie 응답헤더로 전달되므로 개행문자열 포함 여부 검증이 필요
        /* FLAW : CWE-113 */
        Cookie c = new Cookie("LASTLOGIN", lastLogin);
        c.setMaxAge(1000);
        c.setSecure(true);
        response.addCookie(c);
        response.setContentType("text/html");
    }

    public void good(HttpServletRequest request, HttpServletResponse response){
        String lastLogin = request.getParameter("last_login");
        if (lastLogin == null || "".equals(lastLogin)) {
            return;
        }
        // 외부입력값에서 개행문자를 제거한 후 쿠키의 값으로 설정
        lastLogin = lastLogin.replaceAll("[\\r\\n]", "");
        Cookie c = new Cookie("LASTLOGIN", lastLogin);
        c.setMaxAge(1000);
        c.setSecure(true);
        response.addCookie(c);
        response.setContentType("text/html");
    }
}
