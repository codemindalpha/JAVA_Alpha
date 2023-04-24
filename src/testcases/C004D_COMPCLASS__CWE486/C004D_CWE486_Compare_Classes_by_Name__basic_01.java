package testcases.C004D_COMPCLASS;/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE486_Compare_Classes_by_Name__basic_01.java
Label Definition File: CWE486_Compare_Classes_by_Name__basic.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 486 Compare Classes by Name
* Sinks:
*    GoodSink: properly compare class
*    BadSink : Compare class names
* Flow Variant: 01 Baseline
*
* */


import testcasesupport.IO;

public class C004D_CWE486_Compare_Classes_by_Name__basic_01
{
    public static class A {
      public static class Helper {
      }
    }

    public static class B {
      public static class Helper {
      }
    }

    public void bad() throws Throwable
    {

        /* FLAW: Differentiating by name is not enough, since different classes in different packages may use the same name */

        A.Helper helperClass = new A.Helper();
        B.Helper helperClassRoot = new B.Helper();

        if (helperClassRoot.getClass().getSimpleName().equals(helperClass.getClass().getSimpleName()))
        {
            IO.writeLine("Classes are the same");
        }
        else
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            IO.writeLine("Classes are different");
        }

    }

    public void good() throws Throwable
    {
        good1();
    }

    private void good1() throws Throwable
    {
        A.Helper helperClass = new A.Helper();
        B.Helper helperClassRoot = new B.Helper();

        /* FIX: Compare the class types and not the names */
        if (helperClassRoot.getClass().equals(helperClass.getClass()))
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            IO.writeLine("Classes are the same");
        }
        else
        {
            IO.writeLine("Classes are different");
        }

    }

}

