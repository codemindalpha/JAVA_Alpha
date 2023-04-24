import java.io.IOException;

import javax.ejb.Stateless;

/*

by juho 17.12.11
## An EJB is also restricted from creating a custom class loader and creating a class and instance of a class from the class loader, as shown in the following example.
*/

public class C0074_CWE578_Use_of_Class_Loader__bad_02 {

	@Stateless
	public class Bad implements LoaderSessionRemote {
		public Bad() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
			// FLAW:
			ClassLoader loader = new CustomClassLoader();
			Class c = loader.loadClass("someClass");
			Object obj = c.newInstance();
			/*
			 * perform some task that uses the new class instance member variables or
			 * functions
			 */
			// ...
		}

		public class CustomClassLoader extends ClassLoader {
		}
	}

	public interface LoaderSessionRemote{ int action = 0; }

}
