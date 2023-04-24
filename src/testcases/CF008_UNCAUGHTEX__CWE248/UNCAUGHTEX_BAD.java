package CF008_UNCAUGHTEX__CWE248;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
public class UNCAUGHTEX_BAD{
    public static void main(String[] args) {

    }
    public class postEx {
        protected void postEx(HttpServletRequest req, HttpServletResponse res) throws IOException {
            String ip = req.getRemoteAddr();
            InetAddress addr = InetAddress.getByName(ip);
            //...
            ServletOutputStream out = null;
            out.println("your host name is: " + addr.getHostName());
        }
    }
}
