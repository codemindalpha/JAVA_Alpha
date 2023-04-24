package testcases.C002B_SYSTEMLEAK__CWE497_Exposure_of_System_Data.s01;

import testcasesupport.IO;

import java.io.IOException;
import java.util.logging.Level;

public class C002B_SYSTEMLEAK__simple_01 {

    public void bad() {
        try {
            func();
        } catch (IOException e) {
            // 오류 발생시 화면에 출력된 시스템 정보를 통해 다른 공격의 빌미를 제공 한다.
            /* FLAW : CWE-497 */
            System.err.print(e.getMessage());
        }
    }

    public void good() {
        try { func(); }
        catch (IOException e) {
            // 오류와 관련된 최소한의 정보만을 제공하도록한다.
            IO.logger.log(Level.WARNING,"IOException Occured : ", e.toString());
        }
    }

    public void func() throws IOException {
        byte[] testList = {'c', 'o', 'd', 'e'};
        System.out.write(testList);
    }
}
