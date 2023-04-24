package testcases.C0078_FBNOTEVALI;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class C0078_CWE104_Form_Bean_Does_Not_Extend_Validation_Class__bad_01 {
	/*
	## In the following Java example the class RegistrationForm is a Struts framework ActionForm Bean that will maintain user information from a registration webpage for an online business site. The user will enter registration data and through the Struts framework the RegistrationForm bean will maintain the user data.
	*/
	// FLAW:
	public class RegistrationFormBad extends org.apache.struts.action.ActionForm {
		// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormBad() {  super();  }
		// getter and setter methods for private variables
	}

	/*
	## However, the RegistrationForm class extends the Struts ActionForm class which does not allow the RegistrationForm class to use the Struts validator capabilities. When using the Struts framework to maintain user data in an ActionForm Bean, the class should always extend one of the validator classes, ValidatorForm, ValidatorActionForm, DynaValidatorForm or DynaValidatorActionForm. These validator classes provide default validation and the validate method for custom validation for the Bean object to use for validating input data. The following Java example shows the RegistrationForm class extending the ValidatorForm class and implementing the validate method for validating input data.
	*/
	/*
	//good
	public class RegistrationFormGood extends org.apache.struts.validator.ValidatorForm {
	// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormGood() {  super();  }
		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) { ActionErrors errors = super.validate(mapping, request);
			return errors; }
		// getter and setter methods for private variables
	}
	*/
	/*
	## Note that the ValidatorForm class itself extends the ActionForm class within the Struts framework API.
	*/

}
