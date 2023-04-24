package testcases.C610C_WRAPLINE;

public class C610C_WRAPLINE__simple_01 {

    public int plus(int a, int b, int c){
        return a + b + c;
    }
    public void bad(){
        int sum = 1 + 2 + 3 * (4
+ 5);

        int a = 1;
        int b = 5;
        int c = 10;

        int plus = plus(a, b
, c);

    }
}
