package testcases.CF012_USESENSITIVECLS__CWE492;

public class CF012_USESENSITIVECLS__CWE492_SensitiveGood_2 {
    // private member variables of Outer Class
    private String memberOne;
    private static String memberTwo;

    // constructor of OuterClass
    public CF012_USESENSITIVECLS__CWE492_SensitiveGood_2(String varOne, String varTwo) {
        this.memberOne = varOne;
        this.memberTwo = varTwo;
    }

    // InnerClass is a static inner class of Outer Class
    private static class Inner {

        private String innerMemberOne;

        public Inner(String innerVarOne) {
            this.innerMemberOne = innerVarOne;
        }

        public String concat(String separator) {

            // InnerClass only has access to static member variables of Outer Class
            return memberTwo + separator + this.innerMemberOne;
        }
    }

}
