package CF003_CWE107_Unused_Validation_Form;

public class Regist extends org.apache.struts.validator.ValidatorForm{
    public static void main(String[] args) {

    }

    // private variables for registration form
    private String name;
    private String addr;
    private String city;
    private String zipcode;

    // no longer using the phone form field
    // private String phone;
    private String email;

    public Regist() {
        super();
    }

        //...
}
