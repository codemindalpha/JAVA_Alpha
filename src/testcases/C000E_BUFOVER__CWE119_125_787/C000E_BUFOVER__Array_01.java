package testcases.C000E_BUFOVER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C000E_BUFOVER__Array_01 {
    public String bad(HttpServletRequest request, String[] data) throws Throwable {
        String indexString = request.getParameter("index");
        int index = Integer.parseInt(indexString);
        /* FLAW */
        return data[index];
    }
    public String good(HttpServletRequest request, String[] data) throws Throwable {
        String indexString = request.getParameter("index");
        int index = Integer.parseInt(indexString);
        if(index < data.length)
            return data[index];
        return null;
    }
}
