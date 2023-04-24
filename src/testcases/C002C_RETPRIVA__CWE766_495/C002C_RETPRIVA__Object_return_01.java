package testcases.C002C_RETPRIVA__CWE766_495;

import testcasesupport.IO;

import java.util.logging.Level;

public class C002C_RETPRIVA__Object_return_01 {

    private Color[] colors;

    public Color[] getUserColors (java.awt.Color[] userColors) {
        /* FLAW : CWE-766,495 */
        return colors;
    }

    private Color[] getUserColors2(java.awt.Color[] userColors) {
        //배열을 복사한다.
        try {
            Color[] colors = new Color[userColors.length];
            for (int i = 0; i < colors.length; i++) {
                //clone()메소드를 이용하여 배열의 원소도 복사한다.
                colors[i] = (Color) this.colors[i].clone();
            }
        } catch (CloneNotSupportedException e){
            IO.logger.log(Level.WARNING, e.toString());
        }
        return colors;
    }

    public void bad() throws CloneNotSupportedException {
        java.awt.Color[] user_color = new java.awt.Color[]{java.awt.Color.red, java.awt.Color.black, java.awt.Color.blue};
        /* FLAW : CWE-766,495 */
        Color[] color = getUserColors(user_color);
    }

    public void good() {
        java.awt.Color[] user_color = new java.awt.Color[]{java.awt.Color.red, java.awt.Color.black, java.awt.Color.blue};
        Color[] color = getUserColors2(user_color);
    }


}
