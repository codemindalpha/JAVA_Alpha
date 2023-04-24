package testcases.C007A_PLUGNUSE;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class C007A_CWE106_Plug_in_Framework_not_in_Use__01 {
	/*
	## In the following Java example the class RegistrationForm is a Struts framework ActionForm Bean that will maintain user input data from a registration webpage for an online business site. The user will enter registration data and, through the Struts framework, the RegistrationForm bean will maintain the user data.
	*/
	public class RegistrationFormBad extends org.apache.struts.validator.ValidatorForm {
	// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormBad() {  super();  }
		// getter and setter methods for private variables
	}

	/*
	## However, the RegistrationForm class extends the Struts ActionForm class which does use the Struts validator plug-in to provide validator capabilities. In the following example, the RegistrationForm Java class extends the ValidatorForm and Struts configuration XML file, struts-config.xml, instructs the application to use the Struts validator plug-in.
	*/
	// Good Java
	public class RegistrationFormGood extends org.apache.struts.validator.ValidatorForm {
	// private variables for registration form
		private String name;
		private String email;
		public RegistrationFormGood() {  super();  }

		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) { return null; }
		// getter and setter methods for private variables
	}

	/*

	<!--
	## The plug-in tag of the Struts configuration XML file includes the name of the validator plug-in to be used and includes a set-property tag to instruct the application to use the file, validator-rules.xml, for default validation rules and the file, validation.XML, for custom validation.
	-->
	<!-- Good XML -->
	<struts-config>
		<form-beans>
			<form-bean name="RegistrationForm" type="RegistrationForm"/>
		</form-beans>
		...
		<!-- ========================= Validator plugin ================================= -->
		<plug-in className="org.apache.struts.validator.ValidatorPlugIn">
			<set-property
				property="pathnames"
				value="/WEB-INF/validator-rules.xml,/WEB-INF/validation.xml"/>
		</plug-in>
	</struts-config>
	*/
}
