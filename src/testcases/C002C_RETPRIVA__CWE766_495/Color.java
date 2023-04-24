package testcases.C002C_RETPRIVA__CWE766_495;

import java.awt.*;

public class Color implements Cloneable{

    private java.awt.Color[] colors;

    public Color() {
    }

    public Color(java.awt.Color[] colors) {
        this.colors = colors;
    }

    public java.awt.Color[] getColors() {
        return colors;
    }

    public void setColors(java.awt.Color[] colors) {
        this.colors = colors;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
