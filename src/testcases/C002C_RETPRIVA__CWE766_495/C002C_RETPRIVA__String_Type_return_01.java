package testcases.C002C_RETPRIVA__CWE766_495;/*
Filename : CWE495_Private_Array_Typed_Field_Returned_From_A_Public_Method__basic_return_01_bad.java
*/


public class C002C_RETPRIVA__String_Type_return_01
{

    private byte[] foo = null;
    public byte[] getFoo() {
		/* FLAW */
        return foo;
    }

    public byte[] getFoo2() {
        byte[] ret = null;
        if( this.foo != null){
            ret = new byte[foo.length];
            for (int i = 0; i < foo.length; i++){
                ret[i] = this.foo[i];
            }
        }
        return foo;
    }

    public void bad() {
        /* FLAW */
        byte[] tmp_data = getFoo();
    }

    public void good() {
        byte[] tmp_data = getFoo2();
    }


}

