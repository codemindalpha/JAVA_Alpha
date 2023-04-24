import java.io.IOException;

import javax.ejb.Stateless;

/*

by juho 17.12.11
## An EJB is also restricted from creating a custom class loader and creating a class and instance of a class from the class loader, as shown in the following example.
*/

public class C0074_CWE578_Use_of_Class_Loader__good_02 {

	@Stateless
	public class Good implements LoaderSessionRemote {
		public Good() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
			// Do not use CustomClassLoader
		}

		//public class CustomClassLoader extends ClassLoader {
		//}
	}

	public interface LoaderSessionRemote{ int action = 0; }

}
