package testcases.CF013_CRITICALVAR__CWE493;

import java.applet.Applet;

public class CF013_CRITICALVAR__CWE493_CriticalVarBad extends Applet {
    public float price;
    //...

    public CF013_CRITICALVAR__CWE493_CriticalVarBad() {
        this.price = LookupPrice("MyWidgetType");
    }

    public float LookupPrice(String type){
        if(type.equals("MyWidgetType"))
            return 1000;
        else
            return 100;
    }
}
