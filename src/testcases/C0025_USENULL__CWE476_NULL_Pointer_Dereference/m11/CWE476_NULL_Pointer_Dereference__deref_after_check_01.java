/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE476_NULL_Pointer_Dereference__deref_after_check_01.java
Label Definition File: CWE476_NULL_Pointer_Dereference.pointflaw.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 476 NULL Pointer Dereference
* Sinks: deref_after_check
*    GoodSink: Do not dereference an object if it is null
*    BadSink : Dereference after checking to see if an object is null
* Flow Variant: 01 Baseline
*
* */

package testcases.C0025_USENULL__CWE476_NULL_Pointer_Dereference.m11;

import testcasesupport.*;

public class CWE476_NULL_Pointer_Dereference__deref_after_check_01 extends AbstractTestCase
{
    public void bad() throws Throwable
    {

        {
            /* FLAW: Check for null, but still dereference the object */
            String myString = null;

            if (myString == null)
            {
                IO.writeLine(myString.length());
            }
        }

    }

    public void good() throws Throwable
    {
        good1();
    }

    private void good1() throws Throwable
    {

        {
            /* FIX: Check for null and do not dereference the object if it is null */
            String myString = null;

            if (myString == null)
            {
                IO.writeLine("The string is null");
            }
        }

    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}

