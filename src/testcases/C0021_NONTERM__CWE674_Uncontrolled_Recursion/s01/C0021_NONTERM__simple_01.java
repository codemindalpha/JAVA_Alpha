package testcases.C0021_NONTERM__CWE674_Uncontrolled_Recursion.s01;

import testcasesupport.IO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class C0021_NONTERM__simple_01 {

    public void bad() {
        int num = factorial_loop(1000);
    }

    public void good() {
        int num = factorial(1000);
    }

    private int factorial_loop(int n) {
        /* FLAW : CWE-674 */
        return n * factorial_loop(n - 1);
    }

    private int factorial(int n) {
        int i;
        // 제어문을 통해 루프를 빠져나올 수 있게 기술되어야 한다.
        if (n == 1) {
            i = 1;
        } else {
            i = n * factorial(n - 1);
        }
        return i;
    }

}
