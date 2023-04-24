package testcases.C0011_IMPRAUTH__CWE285;

import org.apache.catalina.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C0011_IMPRAUTH__simple_01 {

    public void bad(HttpServletRequest request, HttpServletResponse response)
    {
        String action= request.getParameter("action");
        // 요청을 하는 사용자의 delete 작업 권한 확인 없이 수행하고 있어 안전하지 않다.
        /* FLAW : CWE-285 */
        if (action != null && action.equals("delete")) {
            // 삭제작업을 수행한다.
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action= request.getParameter("action");
        // 세션에 저장된 사용자 정보를 얻어온다.
        User user= (User)session.getAttribute("user");
        // 사용자정보에서 해당 사용자가 delete작업의 권한이 있는지 확인한 뒤 삭제 작업을 수행한다.
        if (action != null && action.equals("delete") && checkAccessControlList(user,action)) {
            // 삭제작업을 수행한다.
        }
    }

    private boolean checkAccessControlList(User user, String action) {
        if("admin".equals(user.getUsername()) && "zazxscd".equals(action))
            return true;
        else
            return false;
    }
}
