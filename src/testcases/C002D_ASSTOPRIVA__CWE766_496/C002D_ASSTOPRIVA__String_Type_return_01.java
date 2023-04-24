package testcases.C002D_ASSTOPRIVA__CWE766_496;/*
Filename : CWE496_Public_Data_Assigned_to_Private_Array_Typed_Field__Environment_2_assign_01_bad.java
*/



import java.util.logging.Logger;

public class C002D_ASSTOPRIVA__String_Type_return_01
{


    private byte[] foo = {};
    public void setFoo(byte[] foo)
    {
		/* FLAW : CWE-776, 496 */
    	this.foo = foo;
    }

    public void setFoo2(byte[] foo)
    {
        this.foo = new byte[foo.length];
        for (int i = 0; i < foo.length; i++){
            this.foo[i] = foo[i];
        }
    }

    public void bad(){
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        /* get environment variable ADD */
        data = System.getenv("ADD");

        /* FLAW : CWE-776, 496 */
		setFoo(data.getBytes());

    }

    public void good(){
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        /* get environment variable ADD */
        data = System.getenv("ADD");

        setFoo2(data.getBytes());

    }

}

