import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class C0077_CWE103_Incomplete_validate_Method_Definition__01 {
	/*
	## In the following Java example the class RegistrationForm is a Struts framework ActionForm Bean that will maintain user input data from a registration webpage for an online business site. The user will enter registration data and the RegistrationForm bean in the Struts framework will maintain the user data. The RegistrationForm class implements the validate method to validate the user input entered into the form.
	*/
	public class RegistrationFormIBad extends org.apache.struts.validator.ValidatorForm {
		// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormIBad() {  super();  }
		public ActionErrors validateBad(ActionMapping mapping, HttpServletRequest request) {
			// FLAW:
			ActionErrors errors = new ActionErrors();
			if (getName() == null || getName().length() < 1)
				errors.add("name", new ActionMessage("error.name.required"));
			return errors;
		}
		// getter and setter methods for private variables
	}

	/*
	## Although the validate method is implemented in this example the method does not call the validate method of the ValidatorForm parent class with a call super.validate(). Without the call to the parent validator class only the custom validation will be performed and the default validation will not be performed. The following example shows that the validate method of the ValidatorForm class is called within the implementation of the validate method.
	*/
	// Good
	public class RegistrationFormIGood extends org.apache.struts.validator.ValidatorForm {
	// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormIGood() {  super();  }
		public ActionErrors validateGood(ActionMapping mapping, HttpServletRequest request) {
			ActionErrors errors = super.validate(mapping, request);
			if (errors == null)
				errors = new ActionErrors();
			if (getName() == null || getName().length() < 1)
				errors.add("name", new ActionMessage("error.name.required"));
			return errors;
		}
		// getter and setter methods for private variables
	}
	public String getName() { return "name"; }
}
