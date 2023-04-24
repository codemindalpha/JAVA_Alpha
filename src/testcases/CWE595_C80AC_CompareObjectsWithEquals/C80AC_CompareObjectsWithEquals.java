package CWE595_C80AC_CompareObjectsWithEquals;

public class C80AC_CompareObjectsWithEquals {

    public void bad(){
        String badStr1 = new String("Hello");
        String badStr2 = new String("Hello");

        if(badStr1 == badStr2){
            System.out.println("badStr1 == badStr2");
        }
    }

    public void good(){
        String goodStr1 = new String("Hello");
        String goodStr2 = new String("Hello");

        if(goodStr1.equals(goodStr2)){
            System.out.println("goodStr1 == goodStr2");
        }
    }
}
