package testcases.C6109_INDSWITCH;

public class C6109_INDSWITCH__simple_01 {
    public void bad(){
        int month = 1;
        String monthString = "";

        switch
        (month){
            case 1: monthString = "January";
                    break;
            case 2: monthString = "February";
                break;
            case 3: monthString = "March";
                break;
            case 4: monthString = "April";
                break;
            case 5: monthString = "May";
                break;
            default: monthString = "Invalid Month";
                break;
        }
        System.out.println(monthString);
    }
}
