package testcases.C00A3_NONEXCUTEPRIMETHOD__CWE561_Dead_Code;

public class C00A3_CWE561_Dead_Code_01_bad {
    private void doTweedledee() { // FLAW
        doTweedledumb();
    }
    private void doTweedledumb() { // FLAW
        doTweedledee();
    }
    public static void main(String[] args) {
        System.out.println("running DoubleDead");
    }
}
