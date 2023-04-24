package testcases.C6107_INDFORSTMT;

public class C6107_INDFORSTMT__simple_01 {
    public void bad(){
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;

        for(int i = 0; i < num1 && i < num2 | i < num3; i++){
            System.out.println("6107_INDFORSTMT 검출");
        }
    }
}