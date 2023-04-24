package testcases.C0051_FINALIZE;/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE586_Explicit_Call_to_Finalize__basic_01.java
Label Definition File: CWE586_Explicit_Call_to_Finalize__basic.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 586 Explicit call to finalize()
* Sinks:
*    GoodSink: let garbage collection invoke finalize
*    BadSink : explictly calling finalize()
* Flow Variant: 01 Baseline
*
* */



import testcasesupport.*;

// compile error
public class C0051_CWE586_Explicit_Call_to_Finalize__basic_01
{
    public static class CWE586_Explicit_Call_to_Finalize__basic_Helper {
        // public void sayHello() {
        //     System.out.println("Hello");
        // }
    }
    public void bad() throws Throwable
    {

        // CWE586_Explicit_Call_to_Finalize__basic_Helper badObj = new CWE586_Explicit_Call_to_Finalize__basic_Helper();

        // try
        // {
        //     badObj.sayHello();
        // }
        // catch (Exception exception)
        // {
        //     IO.writeLine("An error occurred.");
        // }
        // finally
        // {
        //     /* cleanup */
        //     /* FLAW: avoid explicitly invoking the finalize method on an object */
        //     badObj.finalize();
        // }

    }

    public void good() throws Throwable
    {
        // good1();
    }

    private void good1() throws Throwable
    {

        // CWE586_Explicit_Call_to_Finalize__basic_Helper goodObj = new CWE586_Explicit_Call_to_Finalize__basic_Helper();

        // try
        // {
        //     goodObj.sayHello();
        // }
        // catch (Exception exception)
        // {
        //     IO.writeLine("An error occurred.");
        // }
        // finally
        // {
        //     /* cleanup */
        //     /* FIX: set reference to null and garbage collector will eventually finalize the object */
            // goodObj = null;
        // }

    }
}

