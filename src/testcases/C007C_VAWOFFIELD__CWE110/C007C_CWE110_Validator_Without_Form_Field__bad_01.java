import org.apache.struts.validator.ValidatorForm;

public class C007C_CWE110_Validator_Without_Form_Field__bad_01 {
	/*
	## description
	Validation fields that do not appear in forms they are associated with indicate that the validation logic is out of date.

	## demonstrative examples
	This example shows an inconsistency between an action form and a validation form. with a third field.
	This first block of code shows an action form that has two fields, startDate and endDate.
	*/
	public class DateRangeForm extends ValidatorForm {
		String startDate, endDate;
		public void setStartDate(String startDate) { this.startDate = startDate; }
		public void setEndDate(String endDate) { this.endDate = endDate; }
	}
}


/*
<!--

	This second block of related code shows a validation form with a third field: scale. The presence of the third field suggests that DateRangeForm was modified without taking validation into account.
-->

<form name="DateRangeForm">
<field property="startDate" depends="date">
<arg0 key="start.date"/>

</field>
<field property="endDate" depends="date">
<arg0 key="end.date"/>

</field>
<field property="scale" depends="integer">
<arg0 key="range.scale"/>

</field>

</form>
*/
