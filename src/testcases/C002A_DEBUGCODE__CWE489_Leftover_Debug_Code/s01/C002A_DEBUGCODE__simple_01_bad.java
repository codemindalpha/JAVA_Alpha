package testcases.C002A_DEBUGCODE__CWE489_Leftover_Debug_Code.s01;

public class C002A_DEBUGCODE__simple_01_bad {

    public static void main(String[] args) {
        Boolean debug = false;
        /* FLAW : CWE-489 */
        if (debug) {
            byte[] a = { (byte) 0xfc, (byte) 0x0f, (byte) 0xc0 };
            byte[] b = { (byte) 0x03, (byte) 0xf0, (byte) 0x3f };
        }
    }
}
