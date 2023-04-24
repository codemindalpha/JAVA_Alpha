package testcases.C000E_BUFOVER;

import javax.servlet.http.HttpServletRequest;

public class C000E_BUFOVER__Array_02 {
    public String bad(HttpServletRequest request, String[] data) throws Throwable {
        int x = data.length;
        /* FLAW:  */
        return data[x];
    }
    public String good(HttpServletRequest request, String[] data) throws Throwable {
        int x = data.length;
        /* FIX:  */
        return data[x-1];
    }
}
