package testcases.C0028_UNINIT__CWE457_UNINIT;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class C0028_UNINIT__simple_01 {

    public void bad(InputStream in){
        // 초기화되지 않은 변수
        String service;
        Properties props = new Properties();

        try {
            if (in != null && in.available() > 0) {
                props.load(in);
                service = props.getProperty("Service NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 초기화되지 않은 변수 service가 사용되어 예상하지 못한 동작을 수행할 수 있다.
        /* FLAW : CWE-457 */
        //if ("".equals(service)) service = "8080";

        //int port = Integer.parseInt(service);
    }

    public void good(InputStream in){
        // 초기화되지 않은 변수
        String service = "";
        Properties props = new Properties();

        try {
            if (in != null && in.available() > 0) {
                props.load(in);
                service = props.getProperty("Service NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 초기화되지 않은 변수 service가 사용되어 예상하지 못한 동작을 수행할 수 있다.
        if ("".equals(service)) service = "8080";

        int port = Integer.parseInt(service);
    }
}
