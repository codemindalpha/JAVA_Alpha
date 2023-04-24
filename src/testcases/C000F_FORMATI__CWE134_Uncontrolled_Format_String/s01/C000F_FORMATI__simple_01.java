package testcases.C000F_FORMATI__CWE134_Uncontrolled_Format_String.s01;

import java.util.Calendar;

public class C000F_FORMATI__simple_01 {

    public void bad(String[] args) {
        Calendar validDate = Calendar.getInstance();
        validDate.set(2014, Calendar.OCTOBER, 14);
        if (args.length <= 0) {
            System.out.println("Please Input Date (YYYY-MM-DD) !!!");
            return;
        }
        /* FLAW : CWE-134 */
        System.out.printf( args[0] + " did not match! HINT: It was issued on %1$terd of some month", validDate);
    }

    public void good(String[] args) {
        Calendar validDate = Calendar.getInstance();
        validDate.set(2014, Calendar.OCTOBER, 14);
        if (args.length <= 0) {
            System.out.println("Please Input Date (YYYY-MM-DD) !!!");
            return;
        }
        System.out.printf("%s did not match! HINT: It was issued on %2$terd of some month", args[0], validDate);
    }
}
