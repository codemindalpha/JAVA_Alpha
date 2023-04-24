package testcases.C0013_BROKNCRYPT__CWE327_Use_Broken_Crypto.s01;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class C0013_BROKNCRYPT__simple_01 {

    public class Bad {
        public byte[] bad(byte[] msg, Key k) {
            byte[] rslt = null;
            try {
                /* FLAW : CWE-327 */
                Cipher c = Cipher.getInstance("DES");
                c.init(Cipher.ENCRYPT_MODE, k);
                rslt = c.update(msg);
            } catch (InvalidKeyException e) {
                System.err.println("Exception occured!");
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Exception occured!");
            } catch (NoSuchPaddingException e) {
                System.err.println("Exception occured!");
            }
            return rslt;
        }
    }

    public class Good {
        public byte[] good(byte[] msg, Key k) {
            byte[] rslt = null;
            try {
                /* FIX */
                Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
                c.init(Cipher.ENCRYPT_MODE, k);
                rslt = c.update(msg);
            } catch (InvalidKeyException e) {
                System.err.println("Exception occured!");
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Exception occured!");
            } catch (NoSuchPaddingException e) {
                System.err.println("Exception occured!");
            }
            return rslt;
        }
    }
}
