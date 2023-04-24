package testcases.C00A3_NONEXCUTEPRIMETHOD__CWE561_Dead_Code;

public class C00A3_CWE561_Dead_Code_01_good {
    private static void doTweedledee() {
        doTweedledumb();
    }
    private static void doTweedledumb() {
        doTweedledee();
    }
    public static void main(String[] args) {
        doTweedledumb();
        System.out.println("running doTweedledumb");
    }
}
