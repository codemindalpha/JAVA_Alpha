package CF008_UNCAUGHTEX__CWE248;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
public class UNCAUGHTEX_GOOD {
    public static void main(String[] args) {

    }
    protected void postEx(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String ip = req.getRemoteAddr();
            InetAddress addr = InetAddress.getByName(ip);
            //...
            ServletOutputStream out = null;
            out.println("your host name is: " + addr.getHostName());
        }catch (Exception e){
            System.err.println("Error: Cannot get HostName");
        }
    }
}