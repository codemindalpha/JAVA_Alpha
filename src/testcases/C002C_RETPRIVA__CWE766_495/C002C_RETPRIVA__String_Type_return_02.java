package testcases.C002C_RETPRIVA__CWE766_495;

import java.util.Arrays;

public class C002C_RETPRIVA__String_Type_return_02 {

    public static class Bad {
        private String[] colors;
        public String[] getColors() {
            /* FLAW : CWE-776,495 */
            return this.colors;
        }
        public Bad() {
            this.colors = new String[] { "red", "orange", "yellow", "green", "blue", "indigo",
                    "violet" };
        }
        public void print() { System.out.println(Arrays.toString(this.colors)); }
        public static void main(String[] args) {
            Bad innerData = new Bad();
            innerData.print();
            /* FLAW : CWE-776,495 */
            String[] outerData = innerData.getColors();
            // innerData의 data 배열 수정
            outerData[1] = "blue";
            System.out.println(Arrays.toString(outerData));
            innerData.print();
        }
    }

    public static class Good {
        private String[] colors;
        public String[] getColors() {
            String[] safeArray = null;
            if (this.colors != null) {
                safeArray = new String[this.colors.length];
                for (int i = 0; i < this.colors.length; i++) { safeArray[i] = this.colors[i]; }
            }
            return safeArray;
        }
        public Good() {
            this.colors = new String[] { "red", "orange", "yellow", "green", "blue", "indigo",
                    "violet" };
        }
        public void print() { System.out.println(Arrays.toString(this.colors)); }
        public static void main(String[] args) {
            Good innerData = new Good();
            innerData.print();
            String[] outerData = innerData.getColors();
            outerData[1] = "blue";
            System.out.println(Arrays.toString(outerData));
            innerData.print();
        }
    }
}
