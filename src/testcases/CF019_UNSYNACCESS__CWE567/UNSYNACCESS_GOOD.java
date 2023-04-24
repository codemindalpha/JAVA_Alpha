package CF019_UNSYNACCESS__CWE567;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UNSYNACCESS_GOOD {
    public static class Counter extends HttpServlet {
        static int count = 0;

        protected synchronized void getEx(HttpServletRequest in, HttpServletResponse out) throws ServletException, IOException {
            out.setContentType("text/plain");
            PrintWriter p = out.getWriter();
            count++;
            p.println(count + " hits so far!");
        }
    }
}
