package testcases.CF006_INTCOERCION;

import java.util.Date;

public class CF006_INTCOERCION__IntToLongAndAbsTime {
    public Date bad(int seconds) {
        // flaw: Fails for dates after 2037
        return new Date(seconds * 1000L);
    }
    public Date good(long seconds) {
        return new Date(seconds * 1000L);
    }

    public static void test() {
        CF006_INTCOERCION__IntToLongAndAbsTime intcoercion = new CF006_INTCOERCION__IntToLongAndAbsTime();
        Date limitedDate = intcoercion.bad(2147483647);
        Date date = intcoercion.good(2147489999L);
    }
}
