package testcases.CF012_USESENSITIVECLS__CWE492;

public final class Bad {
    private String badFiled;
    public String str1;

    public Bad(String bad){
        badFiled = bad;
    }

    public void code() {
        private final String str11="TEST";
    }

    public class InnerClass{

        private String innerMethod(){
            str1 = str11;

            return Bad.this.badFiled;
        }
    }

}
