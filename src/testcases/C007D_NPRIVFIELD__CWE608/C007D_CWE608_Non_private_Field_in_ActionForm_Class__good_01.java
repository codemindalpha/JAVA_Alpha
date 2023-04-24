package testcases.C007D_NPRIVFIELD;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class C007D_CWE608_Non_private_Field_in_ActionForm_Class__good_01 {
	/*
		## In the following Java example the class RegistrationForm is a Struts framework ActionForm Bean that will maintain user input data from a registration webpage for a online business site. The user will enter registration data and through the Struts framework the RegistrationForm bean will maintain the user data.
	*/
	/*
	public class RegistrationFormBad extends org.apache.struts.validator.ValidatorForm {
		// variables for registration form
		public String name;
		public String email;
		public RegistrationFormBad() {  super();  }
		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) { return null; }
	}
	*/
/*
## However, within the RegistrationForm the member variables for the registration form input data are declared public not private. All member variables within a Struts framework ActionForm class must be declared private to prevent the member variables from being modified without using the getter and setter methods. The following example shows the member variables being declared private and getter and setter methods declared for accessing the member variables.
*/
	//Good
	public class RegistrationFormGood extends org.apache.struts.validator.ValidatorForm {
		// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormGood() {  super();   }
		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) { return null; }
		// getter and setter methods for private variable)
	}
}
