package testcases.C0029_LEAKSESS__CWE488;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class C0029_LEAKSESS__Controller_good_01 {
	public void good(HttpServletRequest request) {
		// 지역변수로 사용하여 스레드간 공유되지 못하도록 한다.
		int currentPage = Integer.parseInt(request.getParameter("page"));
	}
}
