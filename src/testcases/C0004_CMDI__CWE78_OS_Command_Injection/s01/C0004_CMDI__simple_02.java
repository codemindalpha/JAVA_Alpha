package testcases.C0004_CMDI__CWE78_OS_Command_Injection.s01;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class C0004_CMDI__simple_02 {

    public void bad(HttpServletRequest request) throws IOException {

        String date = request.getParameter("date");
        String command = new String("cmd.exe /c backuplog.bat");
        /* FLAW : CWE-78 */
        Runtime.getRuntime().exec(command + date);

        }

    public void good(HttpServletRequest request) throws IOException {

        String date = request.getParameter("date");
        String command = new String("cmd.exe /c backuplog.bat");
        // 외부로부터 입력 받은 값을 필터링을 통해 우회문자를 제거하여 사용한다.
        date = date.replaceAll("|", "");
        date = date.replaceAll(";", "");
        date = date.replaceAll("&", "");
        date = date.replaceAll(":", "");
        date = date.replaceAll("&gt;", "");
        Runtime.getRuntime().exec(command + date);
    }
}
