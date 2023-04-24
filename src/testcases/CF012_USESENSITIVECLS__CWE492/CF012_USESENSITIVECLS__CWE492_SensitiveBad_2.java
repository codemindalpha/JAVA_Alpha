package testcases.CF012_USESENSITIVECLS__CWE492;

public class CF012_USESENSITIVECLS__CWE492_SensitiveBad_2 {

    // private member variables of Outer class
    private String memberOne;
    private String memberTwo;

    // constructor of Outer Class
    public CF012_USESENSITIVECLS__CWE492_SensitiveBad_2(String varOne, String varTwo) {
        this.memberOne = varOne;
        this.memberTwo = varTwo;
    }

    // Inner Class is a member inner class of Outer class
    private class Inner {
        private String innerMemberOne;

        public Inner(String innerVarOne) {
            this.innerMemberOne = innerVarOne;
        }

        public String concat(String separator) {
            // Inner Class has access to private member variables of Outer Class
            System.out.println("Value of memberOne is: " + memberOne);
            return CF012_USESENSITIVECLS__CWE492_SensitiveBad_2.this.memberTwo + separator + this.innerMemberOne;
        }
    }

}
