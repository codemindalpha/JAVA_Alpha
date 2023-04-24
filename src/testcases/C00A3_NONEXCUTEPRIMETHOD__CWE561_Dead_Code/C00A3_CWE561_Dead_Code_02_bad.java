package testcases.C00A3_NONEXCUTEPRIMETHOD__CWE561_Dead_Code;

public class C00A3_CWE561_Dead_Code_02_bad {
    public void x() {
        y();
    }
    private void y() {
        z();
    }
    private void z() {
        zz();
    }
    private void zz() {
        zzz();
    }
    private void zzz() {
        zzzz();
    }
    private void zzzz() {
        zzzzz();
    }
    private void zzzzz() {
        zzzzzz();
    }
    private void zzzzzz() {
        zzzzzzz();
    }
    private void zzzzzzz() {
        zzzzzzzz();
    }
    private void zzzzzzzz() {
        zzzzzzzzz();
    }
    private void zzzzzzzzz() {
        zzzzzzzzzz();
    }
    private void zzzzzzzzzz() {
        zzzzzzzzzzz();
    }
    private void zzzzzzzzzzz() {

    }


    public void a() {
        b();
    }
    private void b() {

    }
    private void c() { // FLAW
        b();
    }
    private void d() { // FLAW
        c();
        b();
        e();
    }
    private void e() { // FLAW
        b();
        d();
    }
    private void f() { // FLAW
        b();
        g();
    }
    private void g() { // FLAW
        b();
        f();
        h();
    }
    private void h() { // FLAW
        b();
    }

    private void i() {

    }
}
