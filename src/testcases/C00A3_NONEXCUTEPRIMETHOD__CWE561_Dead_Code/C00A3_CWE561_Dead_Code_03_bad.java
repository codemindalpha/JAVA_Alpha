package testcases.C00A3_NONEXCUTEPRIMETHOD__CWE561_Dead_Code;

public class C00A3_CWE561_Dead_Code_03_bad {
    private void i() {

    }
    private void j() {
        i();
    }
    private void k() {
        i();
    }
    private void l() {
        i();
    }
    private void m() {
        j();
    }
    public void n() {
        j();
    }

}

