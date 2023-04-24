package testcases.C001D_ONEWAYHASH__CWE759_Unsalted_One_Way_Hash.s01;

import testcasesupport.IO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class C001D_ONEWAYHASH__simple_01 {

    public void bad() throws NoSuchAlgorithmException {
        String value = getPasswordHash("1234qwer!");
    }

    public void good(){
        byte[] salt = new byte[] { 12, 34, 56, 78, 90 };
        try {
            String value = getPasswordHash("1234qwer!", salt);
        } catch (Exception e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

    public String getPasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        /* FLAW : CWE-759 */
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i=0; i < byteData.length; i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getPasswordHash(String password, byte[] salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        md.update(salt);

        byte byteData[] = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i=0; i < byteData.length; i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
