package testcases.C00A2_ARRAYINDEXVALIDATION__CWE129_Improper_Validation_of_Array_Index;

public class C00A2_CWE129_Improper_Validation_of_Array_Index_01_good {
    String[] array;
    public String getValue(int index) {
        if(index > 0 && array.length - 1 > index) {
            return array[index];
        }
        return null;
    }

    private void buildList ( int untrustedListSize ){
        if ( 1 > untrustedListSize ){
            die("Negative value supplied for list size, die evil hacker!");
        }
        Widget1[] list = new Widget1 [ untrustedListSize ];
        list[0] = new Widget1();
    }

    public void die(String msg) {
        System.out.println(msg);
    }
}

class Widget1 {

}
