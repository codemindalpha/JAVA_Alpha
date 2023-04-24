package testcases.CF020_CWE1235_Incorrect_Use_of_Autoboxing;

public class CF020_CWE1235_Incorrect_Use_of_Autoboxing__simple {
	
	Short a = 2;
	short b = 3;

	public void bad(){
		Long count = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
        count += i;
		}
	}
	
	public void good(){
		long count2 = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
        count += i;
		}
	}
	
		
}
