/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE476_NULL_Pointer_Dereference__Integer_66a.java
Label Definition File: CWE476_NULL_Pointer_Dereference.label.xml
Template File: sources-sinks-66a.tmpl.java
*/
/*
 * @description
 * CWE: 476 Null Pointer Dereference
 * BadSource:  Set data to null
 * GoodSource: Set data to a non-null value
 * Sinks:
 *    GoodSink: add check to prevent possibility of null dereference
 *    BadSink : possibility of null dereference
 * Flow Variant: 66 Data flow: data passed in an array from one method to another in different source files in the same package
 *
 * */

package testcases.C0025_USENULL__CWE476_NULL_Pointer_Dereference.m00;

import testcasesupport.*;

public class CWE476_NULL_Pointer_Dereference__Integer_66a extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        Integer data;

        /* POTENTIAL FLAW: data is null */
        data = null;

        Integer[] dataArray = new Integer[5];
        dataArray[2] = data;
        (new CWE476_NULL_Pointer_Dereference__Integer_66b()).badSink(dataArray  );
    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B() throws Throwable
    {
        Integer data;

        /* FIX: hardcode data to non-null */
        data = Integer.valueOf(5);

        Integer[] dataArray = new Integer[5];
        dataArray[2] = data;
        (new CWE476_NULL_Pointer_Dereference__Integer_66b()).goodG2BSink(dataArray  );
    }

    /* goodB2G() - use badsource and goodsink */
    private void goodB2G() throws Throwable
    {
        Integer data;

        /* POTENTIAL FLAW: data is null */
        data = null;

        Integer[] dataArray = new Integer[5];
        dataArray[2] = data;
        (new CWE476_NULL_Pointer_Dereference__Integer_66b()).goodB2GSink(dataArray  );
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
