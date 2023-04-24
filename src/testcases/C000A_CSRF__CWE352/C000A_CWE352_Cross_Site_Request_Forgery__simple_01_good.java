package testcases.C000A_CSRF__CWE352;/*
Filename : CWE352_Cross_Site_Request_Forgery__getCookiesServlet_write_01_good.java
*/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class C000A_CWE352_Cross_Site_Request_Forgery__simple_01_good {

    public void good(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 요청 파라미터와 세션에 저장된 토큰을 비교해서 일치하는 경우에만 요청을 처리한다.
        String pToken = request.getParameter("param_csrf_token");
        String sToken = (String) session.getAttribute("SESSION_CSRF_TOKEN");
        if (pToken != null && pToken.equals(sToken)) {
            // 일치하는 토큰이 존재하는 경우 -＞ 정상 처리
            response.getWriter().print("#success");
        } else {
            // 토큰이 없거나 값이 일치하지 않는 경우 -＞ 오류 메시지 출력
            response.getWriter().print("#fail");
        }
    }


}

