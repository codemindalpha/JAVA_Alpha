package testcases.C002D_ASSTOPRIVA__CWE766_496;/*
Filename : CWE496_Public_Data_Assigned_to_Private_Array_Typed_Field__Environment_2_assign_01_bad.java
*/



import java.util.Arrays;

public class C002D_ASSTOPRIVA__String_Type_return_02
{
    public static class Bad {
        private String[] datas;
        public void setDatas(String[] datas) {
            /* FLAW : CWE-776, 496 */
            this.datas = datas;
        }
        public Bad() { this.datas = new String[] { "100", "90", "70", "80" }; }
        public void print() { System.out.println(Arrays.toString(this.datas)); }
        public static void main(String[] args) {
            Bad innerData = new Bad();
            innerData.print();
            String[] outerData = new String[] { "10", "20", "30" };
            /* FLAW : CWE-776, 496 */
            innerData.setDatas(outerData);
            innerData.print();
            System.out.println(Arrays.toString(outerData));
            // innerData의 data 배열 수정
            outerData[1] = "xx";
            innerData.print();
            System.out.println(Arrays.toString(outerData));
        }
    }

    public static class Good {
        private String[] datas;
        public void setDatas(String[] datas) {
            if (datas != null) {
                this.datas = new String[datas.length];
                for (int i = 0; i < datas.length; i++) {
                    this.datas[i] = datas[i];
                }
            }
        }
        public Good() { this.datas = new String[] { "100", "90", "70", "80" }; }
        public void print() { System.out.println(Arrays.toString(this.datas)); }
        public static void main(String[] args) {
            Good innerData = new Good();
            innerData.print();
            String[] outerData = new String[] { "10", "20", "30" };
            innerData.setDatas(outerData);
            innerData.print();
            System.out.println(Arrays.toString(outerData));
            outerData[1] = "xx";
            innerData.print();
            System.out.println(Arrays.toString(outerData));
        }
    }


}

