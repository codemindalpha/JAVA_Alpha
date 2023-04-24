package testcases.CF003_CWE107_Unused_Validation_Form;

public class CF003_CWE107_Unused_Validation_Form__simple {
	public class RegistrationVFormBad extends org.apache.struts.validator.ValidatorForm {
		// private variables for registration form
		private String name;
		private String address;
		private String city;
		private String state;
		// no longer using the zipcode form field
		//private String zipcode;

		public RegistrationVFormBad() {  super();  }
		// getter and setter methods for private variables
	}

	public class RegistrationVFormGood extends org.apache.struts.validator.ValidatorForm {
		// private variables for registration form
		private String name;
		private String address;
		private String city;
		private String state;
		private String zipcode;

		public RegistrationVFormGood() {  super();  }
		// getter and setter methods for private variables
	}
}
