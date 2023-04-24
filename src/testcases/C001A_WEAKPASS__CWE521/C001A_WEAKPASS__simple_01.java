package testcases.C001A_WEAKPASS__CWE521;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C001A_WEAKPASS__simple_01 {

    public void bad(HttpServletRequest request){
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        UserVO userVO = new UserVO(id, pass);

        RegistDAO registDAO = new RegistDAO();

        // 비밀번호의 자릿수, 특수문자 포함 여부 등 복잡도를 체크하지 않고 등록
        /* FLAW : CWE521 */
        String result = registDAO.regist(userVO);
    }

    public void good(HttpServletRequest request){
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9@#$%]). {9,})");
        Matcher matcher = pattern.matcher(pass);
        if (!matcher.matches()) {
            IO.writeLine("비밀번호 조합규칙 오류");
        }
        UserVO userVO = new UserVO(id, pass);

        RegistDAO registDAO = new RegistDAO();

        String result = registDAO.regist(userVO);
    }
}
