package testcases.CF013_CRITICALVAR__CWE493;

import java.applet.Applet;

public class CF013_CRITICALVAR__CWE493_CriticalVaGood extends Applet {
    public final float price;
    //...

    public CF013_CRITICALVAR__CWE493_CriticalVaGood() {
        this.price = LookupPrice("MyWidgetType");
    }

    public float LookupPrice(String type){
        if(type.equals("MyWidgetType"))
            return 1000;
        else
            return 100;
    }
}
