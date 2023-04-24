package testcases.CF012_USESENSITIVECLS__CWE492;

public final class Good {
    private String goodField;

    private String Good(String g){
        goodField = g;
        return goodField;
    }

    private class InnerClass{

        private String innerMethod(){

            return Good.this.goodField;
        }

    }
}
