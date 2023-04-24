package testcases.C0079_FFIELDWOVA;

public class C0079_CWE105_Form_Field_Without_Validator__01 {
	public class RegistrationVFormBad extends org.apache.struts.validator.ValidatorForm {
		// private variables for registration form
		private String name;
		private String address;
		private String city;
		private String state;
		private String zipcode;
		// FLAW:
		private String phone;
		// FLAW:
		private String email;

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
		private String phone;
		private String email;

		public RegistrationVFormGood() {  super();  }
		// getter and setter methods for private variables
	}
}
