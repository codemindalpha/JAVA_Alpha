package CWE609_80F4_DoubleCheckedLocking;

public class CWE609_80F4_DoubleCheckedLocking {

    private Helper helper;

    public Helper bad(){
        if (helper == null) {
            synchronized (this) {
                if (helper == null) {
                    helper = new Helper();
                }
            }
        }
        return helper;
    }

    class Helper{
        public void Helper(){}
    }
}
