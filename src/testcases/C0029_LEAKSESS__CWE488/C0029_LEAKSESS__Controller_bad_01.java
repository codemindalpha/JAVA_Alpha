package testcases.C0029_LEAKSESS__CWE488;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class C0029_LEAKSESS__Controller_bad_01 {
	// Controller에서 int필드가 멤버 변수로 선언되어 스레드간에 공유됨
	/* FLAW : CWE-488 */
	private int currentPage = 1;
	public void bad(HttpServletRequest request) {
		currentPage = Integer.parseInt(request.getParameter("page"));
	}
}
