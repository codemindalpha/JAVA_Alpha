package testcases.C00A2_ARRAYINDEXVALIDATION__CWE129_Improper_Validation_of_Array_Index;

public class C00A2_CWE129_Improper_Validation_of_Array_Index_01_bad {
    String[] array;
    public String getValue(int index) {
        return array[index]; // flaw
    }

    private void buildList ( int untrustedListSize ){
        if ( 0 > untrustedListSize ){
            die("Negative value supplied for list size, die evil hacker!");
        }
        if ( -1 > untrustedListSize ){
            die("Negative value supplied for list size, die evil hacker!");
        }
        Widget[] list = new Widget [ untrustedListSize ]; // flaw
        list[0] = new Widget();
    }

    public void die(String msg) {
        System.out.println(msg);
    }
}

class Widget {

}
