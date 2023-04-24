/*
 * @description A user function in this helper class returns null.  Calling methods should 
 *   be checking returned values against null.
 * 
 * */

package testcasesupport;

public class CWE690_NULL_Deref_From_Return__Class_Helper 
{
    public static String getStringBad() 
    {
        return null;    
    }
    
    public static String getStringGood() 
    {
        return "getStringGood";    
    }
    
    public static StringBuilder getStringBuilderBad() 
    {
        return null;
    }
    
    public static StringBuilder getStringBuilderGood()
    {
        return new StringBuilder("getStringBuilderGood");
    }
}
